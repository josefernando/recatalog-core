package br.com.recatalog.core;

import java.security.InvalidParameterException;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import br.com.recatalog.util.BicamSystem;
import br.com.recatalog.util.PropertyList;

public interface Scope {
	public Map<String,Set<Symbol>> getMembers();
	public String toStringAll();

	public PropertyList getProperties();

	
	public default String getName() {
		return (String)getProperties().getProperty("NAME");
	}
	
	public default Scope getScope() {
		Symbol symScope = (Symbol)this;
		if(symScope.getParent() != null) return symScope.getParent();
		return symScope.getScope();
	}
	
	public default boolean isCaseSensitive() {
		Boolean caseSensitive = (Boolean) getProperties().getProperty("CASE_SENSITIVE");
		if(caseSensitive == null) {
			try {
				throw new InvalidParameterException();
			} catch(InvalidParameterException e) {
				BicamSystem.printLog("ERROR", "Invalid NULL property: 'CASE_SENSITIVE'");
			}
		}
		return caseSensitive;
	}
	
	public Symbol resolve(PropertyList properties);

	
//	public default Symbol resolve(PropertyList properties) {
//		String nameToResolve = (String) properties.getProperty("NAME_TO_RESOLVE");
//		
//		ParserRuleContext ctx = (ParserRuleContext)properties.getProperty("CONTEXT");
//		
//		
//		if(nameToResolve.indexOf(".") > -1 || nameToResolve.indexOf("!") > -1) {
//			 return resolveMember(properties);
//		}
//
//		
//		if(nameToResolve.split("[!.]").length > 1) return resolveMember(properties);
//		
//		if(!isCaseSensitive()) nameToResolve = nameToResolve.toUpperCase();
//		
//		Set<Symbol> members = getMembers().get(nameToResolve);
//		
//		Symbol result = null;
//		boolean typeClass = false;
//		
//		if(members == null) {
//			if(getScope() != null) {
//				result = getScope().resolve(properties);
//			}
//			
//			if (result != null) return result;
//			
//			Scope s = (Scope)getProperties().getProperty("TYPE");
//			if(s != null && s.getMembers().get(nameToResolve) != null) {
//					members = s.getMembers().get(nameToResolve);
//					typeClass = true;
//			}
//			else if((Boolean)properties.mustProperty("OPTION_EXPLICIT") == false) {
//				PropertyList propImplicit = properties.getCopy();
//				propImplicit.addProperty("NAME", (String) properties.mustProperty("NAME_TO_RESOLVE"));
//				propImplicit.addProperty("SCOPE", (Scope) properties.mustProperty("SCOPE_TO_RESOLVE"));
//				propImplicit.addProperty("DEF_MODE", "IMPLICIT");
//				propImplicit.addProperty("LANGUAGE", getProperties().getProperty("LANGUAGE"));
//				
//				propImplicit.addProperty("SYMBOL_TYPE", SymbolType.IMPLICIT);
//				SymbolFactory sf = Vb6SymbolFactory.getInstance("VB6");
//				
//				Symbol sym = sf.getSymbol(propImplicit);
//				sym.addProperty("DATA_TYPE", "Variant");
//				
//				PropertyList propScope = properties.getCopy();
//				Scope scopex = (Scope) properties.mustProperty("SCOPE_TO_RESOLVE");
//				propScope.addProperty("NAME_TO_RESOLVE", "Variant");
//				Type type = (Type) scopex.resolve(propScope);
//				sym.setType(type);
//				return sym;
//			}
//			else return result;
//		}
//		
//		if(members.size() == 1) {
//			List<Symbol> symbols = new ArrayList<Symbol>(members);
//			Symbol sym = symbols.get(0);
//
//			result = symbols.get(0);
//			if(typeClass) {
//				PropertyList propImplicit = properties.getCopy();
//				propImplicit.addProperty("NAME", (String) properties.getProperty("NAME_TO_RESOLVE"));
//				propImplicit.addProperty("SCOPE", this);
//				propImplicit.addProperty("DEF_MODE", "IMPLICIT");
//				propImplicit.addProperty("LANGUAGE", getProperties().getProperty("LANGUAGE"));
//				
//				propImplicit.addProperty("SYMBOL_TYPE", SymbolType.IMPLICIT);
//				
//				SymbolFactory sf = Vb6SymbolFactory.getInstance("VB6");
//
//				result = sf.getSymbol(propImplicit);
//			}
//					
//			return result;
//		}
//		
//		if(members.size() > 1) {
//
//			List<Symbol> symbols = new ArrayList<Symbol>(members);
//			String instanceOf = (String) properties.getProperty("INSTANCE_OF");
//			
//			
//			if (instanceOf != null) {
//				for(Symbol sym : symbols) {
//					if(instanceOf.equalsIgnoreCase("TYPE")) {
//						if(Type.class.isInstance(sym)) return sym;
//					}
//					if(instanceOf.equalsIgnoreCase("VARIABLE")) {
//						if(VariableSymbol.class.isInstance(sym)) return sym;
//					}
//					if(instanceOf.equalsIgnoreCase("CLASS")) {
//						if(ModuleClsSymbol.class.isInstance(sym)) return sym;
//					}					
//					if(instanceOf.equalsIgnoreCase("GUI")) {
//						if(GuiSymbol.class.isInstance(sym)) return sym;
//					}
//					if(instanceOf.equalsIgnoreCase("STRUCTURE")) {
//						if(StructureSymbol.class.isInstance(sym)) return sym;
//					}
//				}				
//			}
//			else { // escolhe arbitrariamente o symbol Variable
//				for(Symbol sym : symbols) {
//					if(VariableSymbol.class.isInstance(sym)) return sym;
//				}
//			}
//			
//			Symbol symModule = null;
//			Symbol symGuiForm  = null;
//			for(Symbol sym : symbols) {
//				if(sym.getClass().getSimpleName().equals(ModuleFrmSymbol.class.getSimpleName()))
//					symModule = sym;
//				if(sym.getClass().getSimpleName().equals(GuiSymbol.class.getSimpleName()))
//					symGuiForm = sym;
//			}
//			
//			if(symModule != null && symGuiForm != null) return symGuiForm;
//			
//			if(getScope() != null) {
//				 return getScope().resolve(properties);
//			}
//			
//			DuplicateOnResolve(members);
//			return null;
//		}
//		return null;
//	}
	
	public Symbol resolveMember(PropertyList properties);
	
//	public default Symbol resolveMember(PropertyList properties) {
//		String nameToResolve = (String) properties.getProperty("NAME_TO_RESOLVE");
//		ParserRuleContext ctx = (ParserRuleContext) properties.getProperty("CONTEXT");
//		
//		String[] nameParts = nameToResolve.split("[!.]");
//		
//		Scope scope = null;
//		
//		if(nameParts.length > 1) {
//			StringBuilder sb = new StringBuilder();
//			int ix = nameParts.length-2;
//			sb.append(nameParts[ix--]);
//			for(int i = ix; i > -1; i--){
//					sb.insert(0,  nameParts[ix] +  ".");  
//			}
//			PropertyList prop = properties.getCopy();
//			prop.addProperty("NAME_TO_RESOLVE", sb.toString());
//			
//			Symbol symScope = resolveMember(prop);
//			if(symScope == null) {
//				BicamSystem.printLog("WARNING", String.format("Symbol: %s not found in line: %d at position: %d"
//					                       , (String) properties.getProperty("NAME_TO_RESOLVE")
//					                       , ctx.start.getLine(), ctx.start.getCharPositionInLine()));
//				return null;
//		}
//			
//		if((symScope instanceof Scope)) scope = (Scope)symScope;
//		
//		Symbol sym = null;
//		for(Entry<String, Set<Symbol>> e : scope.getMembers().entrySet()) {
//			if(e.getKey().equalsIgnoreCase(nameParts[nameParts.length-1])) {
//				List<Symbol> listOfNames = new ArrayList(e.getValue());
//				sym = listOfNames.get(0);
//			}
//		}
//
//		if(sym== null &&( scope.getProperties().hasProperty("DEF_MODE", "BUILTIN")
//    			          || scope.getProperties().hasProperty("DEF_MODE", "IMPLICIT") 
//				          || scope.getClass().getSimpleName().equals(GuiSymbol.class.getSimpleName()) )) {
//			PropertyList propImplicit = properties.getCopy();
//			propImplicit.addProperty("NAME", nameParts[nameParts.length-1]);
//			propImplicit.addProperty("SCOPE", scope);
//			propImplicit.addProperty("DEF_MODE", "IMPLICIT");
//			propImplicit.addProperty("LANGUAGE", getProperties().getProperty("LANGUAGE"));
//			
//			propImplicit.addProperty("SYMBOL_TYPE", SymbolType.IMPLICIT);
//			sym = tryCreateImplicit(propImplicit);
//		}
//			
//			if(sym == null) sym = tryMemberOfType((Symbol)scope,properties);
//			return sym;
//		}
//		return resolve(properties);
//	}
	
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
	
	public default void DuplicateOnResolve(Set<Symbol> members) {
		StringBuilder sb = new StringBuilder();
		for(Symbol sym : members ) {
			String msg = String.format("Symbol: %s , location: %s%n", sym.getName(),sym.location());
			sb.append(msg);
		}
		
		try {
			String msg = String.format("Resolved on %d symbols.%n%s", members.size(),sb.toString());
			throw new DuplicatedSymbolException(msg);
		}catch (DuplicatedSymbolException e) {
			BicamSystem.printLog("WARNING", e.getMessage());
		}
	}
	
	/*
	 * se for array gui element no form ok, porque tem o mesmo nome e não é duplicado
	 */
	public default boolean isArrayGuiComponent() {
		if(getProperties().hasProperty("ARRAY_INDEX")) return true;
		return false;
	}
	
	public default void duplicateOnAddSymbol(Symbol sym, String ...conflict) {
		String conflicted = "";
		if(conflict.length > 0) conflicted = conflict[0];
		try {
			String msg = String.format("Duplicate symbol '%s' in %s at location %s%n", sym.getName(), conflicted ,sym.location());
			throw new DuplicatedSymbolException(msg);
		} catch (DuplicatedSymbolException e) {
		}
	}
	
	public default Scope getScope(String scopeName) {
		Scope scope = (Scope)getProperties().getProperty("SCOPE");
		if(scope == null) {
			if(getClass().getSimpleName().equals("GlobalScope")) {
				return this;
			} else {
				try {
					throw new  NullPointerException();
				} catch (Exception e) {
					BicamSystem.printLog("ERROR", "INVALID null scope");
				}				
			}
		}
		
		if(scope.getName().equals(scopeName)) return this;
		return this.getScope(scopeName);
	}
	
	public Symbol tryCreateImplicit(PropertyList properties);
	
//	public default Symbol tryCreateImplicit(PropertyList properties) {
//		Scope scope = (Scope)properties.getProperty("SCOPE");
//		if(scope.getProperties().hasProperty("DEF_MODE", "BUILTIN")
//				|| scope.getProperties().hasProperty("DEF_MODE", "IMPLICIT")
//				|| scope.getClass().getSimpleName().equals(GuiSymbol.class.getSimpleName()) ) {
//		
//		SymbolFactory sf = Vb6SymbolFactory.getInstance("VB6");
//		return sf.getSymbol(properties);
//		}
//		return null;
//	}
	
	/**
	 * membros pertence à scope enquanto childen pertencem à parent
	 * 
	 * 
	 */
	public default boolean addMember(Symbol sym) {
		String key = isCaseSensitive() == true ? sym.getName() : sym.getName().toUpperCase();
		Set<Symbol> members = getMembers().get(key);
		if(members == null) {
			members = new LinkedHashSet<Symbol>();
			getMembers().put(key, members);
		}
		else {
			for(Symbol s : members) {
				if(s.equals(sym)) {
					// array gui element pode ser duplicado, porque "index" atributos são diferentes
					if(s.hasProperty("ARRAY_INDEX")) break;
					if(getName().equalsIgnoreCase("GLOBAL")) {
						s.getProperties().addProperty("DUP", sym);
						sym.getProperties().addProperty("DUP", s);
						duplicateOnAddSymbol(sym, "CONFLICTED IN GLOBAL SCOPE");
						break;
					}
					duplicateOnAddSymbol(sym);
					return false;
				}
			}
		}
		return members.add(sym);
	}
}