package br.com.recatalog.core.visualbasic6;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.antlr.v4.runtime.ParserRuleContext;

import br.com.recatalog.core.Scope;
import br.com.recatalog.core.Symbol;
import br.com.recatalog.core.SymbolFactory;
import br.com.recatalog.core.SymbolType;
import br.com.recatalog.core.Type;
import br.com.recatalog.util.BicamSystem;
import br.com.recatalog.util.PropertyList;

public interface ScopeVisualBasic6 extends Scope {
	
	public PropertyList getProperties();
	
	public default Symbol resolve(PropertyList properties) {
		String nameToResolve = (String) properties.getProperty("NAME_TO_RESOLVE");
		
		ParserRuleContext ctx = (ParserRuleContext)properties.getProperty("CONTEXT");
		
		if(nameToResolve.indexOf(".") > -1 || nameToResolve.indexOf("!") > -1) {
			 return resolveMember(properties);
		}
		
		if(nameToResolve.split("[!.]").length > 1) return resolveMember(properties);
		
		if(!isCaseSensitive()) nameToResolve = nameToResolve.toUpperCase();
		
		Set<Symbol> members = getMembers().get(nameToResolve);
		
		Symbol result = null;
		boolean typeClass = false;
		
		if(members == null) {
			if(getScope() != null) {
				result = getScope().resolve(properties);
			}
			
			if (result != null) return result;
			
			if((properties.getProperty("INSTANCE_OF") != null) && ((String)properties.getProperty("INSTANCE_OF")).equalsIgnoreCase("TYPE") )
				 return result;
			
			Scope s = (Scope)getProperties().getProperty("TYPE");
			if(s != null && s.getMembers().get(nameToResolve) != null) {
					members = s.getMembers().get(nameToResolve);
					typeClass = true;
			}
			else if((Boolean)properties.mustProperty("OPTION_EXPLICIT") == false) {
				PropertyList propImplicit = properties.getCopy();
				propImplicit.addProperty("NAME", (String) properties.mustProperty("NAME_TO_RESOLVE"));
				propImplicit.addProperty("SCOPE", (Scope) properties.mustProperty("SCOPE_TO_RESOLVE"));
				propImplicit.addProperty("DEF_MODE", "IMPLICIT");
				propImplicit.addProperty("LANGUAGE", getProperties().getProperty("LANGUAGE"));
				
				propImplicit.addProperty("SYMBOL_TYPE", SymbolType.IMPLICIT);
				SymbolFactory sf = new SymbolFactoryVisualBasic6();
				
				Symbol sym = sf.getSymbol(propImplicit);
				sym.addProperty("DATA_TYPE", "Variant");
				
				PropertyList propScope = properties.getCopy();
				Scope scopex = (Scope) properties.mustProperty("SCOPE_TO_RESOLVE");
				propScope.addProperty("NAME_TO_RESOLVE", "Variant");
				Type type = (Type) scopex.resolve(propScope);
				sym.setType(type);
				return sym;
			}
			else return result;
		}
		
		if(members.size() == 1) {
			List<Symbol> symbols = new ArrayList<Symbol>(members);
			Symbol sym = symbols.get(0);

			result = symbols.get(0);
			if(typeClass) {
				PropertyList propImplicit = properties.getCopy();
				propImplicit.addProperty("NAME", (String) properties.getProperty("NAME_TO_RESOLVE"));
				propImplicit.addProperty("SCOPE", this);
				propImplicit.addProperty("DEF_MODE", "IMPLICIT");
				propImplicit.addProperty("LANGUAGE", getProperties().getProperty("LANGUAGE"));
				
				propImplicit.addProperty("SYMBOL_TYPE", SymbolType.IMPLICIT);
				
				SymbolFactory sf = new SymbolFactoryVisualBasic6();

				result = sf.getSymbol(propImplicit);
			}
					
			return result;
		}
		
		if(members.size() > 1) {
			List<Symbol> symbols = new ArrayList<Symbol>(members);
			String instanceOf = (String) properties.getProperty("INSTANCE_OF");
			
			if (instanceOf != null) {
				for(Symbol sym : symbols) {
					if(instanceOf.equalsIgnoreCase("TYPE")) {
						if(Type.class.isInstance(sym)) return sym;
					}
					if(instanceOf.equalsIgnoreCase("VARIABLE")) {
						if(VariableSymbol.class.isInstance(sym)) return sym;
					}
					if(instanceOf.equalsIgnoreCase("CLASS")) {
						if(ModuleClsSymbol.class.isInstance(sym)) return sym;
					}					
					if(instanceOf.equalsIgnoreCase("GUI")) {
						if(GuiSymbol.class.isInstance(sym)) return sym;
					}
					if(instanceOf.equalsIgnoreCase("STRUCTURE")) {
						if(StructureSymbol.class.isInstance(sym)) return sym;
					}
				}				
			}
			else { // escolhe arbitrariamente o symbol Variable
				for(Symbol sym : symbols) {
					if(VariableSymbol.class.isInstance(sym)) return sym;
				}
			}
			
			Symbol symModule = null;
			Symbol symGuiForm  = null;
			for(Symbol sym : symbols) {
				if(sym.getClass().getSimpleName().equals(ModuleFrmSymbol.class.getSimpleName()))
					symModule = sym;
				if(sym.getClass().getSimpleName().equals(GuiSymbol.class.getSimpleName()))
					symGuiForm = sym;
			}
			
			if(symModule != null && symGuiForm != null) return symGuiForm;
			
			if(getScope() != null) {
				 return getScope().resolve(properties);
			}
			
			DuplicateOnResolve(members);
			return null;
		}
		return null;
	}
	
	public default Symbol resolveMember(PropertyList properties) {
		String nameToResolve = (String) properties.getProperty("NAME_TO_RESOLVE");
		ParserRuleContext ctx = (ParserRuleContext) properties.getProperty("CONTEXT");
		
		String[] nameParts = nameToResolve.split("[!.]");
		
		Scope scope = null;
		
		if(nameParts.length > 1) {
			StringBuilder sb = new StringBuilder();
			int ix = nameParts.length-2;
			sb.append(nameParts[ix--]);
			for(int i = ix; i > -1; i--){
					sb.insert(0,  nameParts[ix] +  ".");  
			}
			PropertyList prop = properties.getCopy();
			prop.addProperty("NAME_TO_RESOLVE", sb.toString());
			
			Symbol symScope = resolveMember(prop);
			if(symScope == null) {
				BicamSystem.printLog("WARNING", String.format("Symbol: %s not found in line: %d at position: %d"
					                       , (String) properties.getProperty("NAME_TO_RESOLVE")
					                       , ctx.start.getLine(), ctx.start.getCharPositionInLine()));
				return null;
		}
			
		if((symScope instanceof Scope)) scope = (Scope)symScope;
		
		Symbol sym = null;
		for(Entry<String, Set<Symbol>> e : scope.getMembers().entrySet()) {
			if(e.getKey().equalsIgnoreCase(nameParts[nameParts.length-1])) {
				List<Symbol> listOfNames = new ArrayList(e.getValue());
				sym = listOfNames.get(0);
			}
		}

		if(sym== null &&( scope.getProperties().hasProperty("DEF_MODE", "BUILTIN")
    			          || scope.getProperties().hasProperty("DEF_MODE", "IMPLICIT") 
				          || scope.getClass().getSimpleName().equals(GuiSymbol.class.getSimpleName()) )) {
			PropertyList propImplicit = properties.getCopy();
			propImplicit.addProperty("NAME", nameParts[nameParts.length-1]);
			propImplicit.addProperty("SCOPE", scope);
			propImplicit.addProperty("DEF_MODE", "IMPLICIT");
			propImplicit.addProperty("LANGUAGE", getProperties().getProperty("LANGUAGE"));
			
			propImplicit.addProperty("SYMBOL_TYPE", SymbolType.IMPLICIT);
			sym = tryCreateImplicit(propImplicit);
		}
			
			if(sym == null) sym = tryMemberOfType((Symbol)scope,properties);
			return sym;
		}
		return resolve(properties);
	}
	
	public default Symbol tryMemberOfType(Symbol sym, PropertyList properties) {
		Symbol ret = null;
		
		Scope symType = (Scope)sym.getType();
		
		String nameToResolve = (String)properties.getProperty("NAME_TO_RESOLVE");
		nameToResolve = nameToResolve.replace(sym.getName(), symType.getName());
		PropertyList prop = properties.getCopy();
		prop.addProperty("NAME_TO_RESOLVE", nameToResolve);
		prop.addProperty("INSTANCE_OF", "STRUCTURE");

		ret = resolveMember(prop);
		return ret;
	}
	
	public default Symbol tryCreateImplicit(PropertyList properties) {
		Scope scope = (Scope)properties.getProperty("SCOPE");
		if(scope.getProperties().hasProperty("DEF_MODE", "BUILTIN")
				|| scope.getProperties().hasProperty("DEF_MODE", "IMPLICIT")
				|| scope.getClass().getSimpleName().equals(GuiSymbol.class.getSimpleName()) ) {
		
		SymbolFactory sf = new SymbolFactoryVisualBasic6();
		return sf.getSymbol(properties);
		}
		return null;
	}
}