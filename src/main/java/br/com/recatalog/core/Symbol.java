package br.com.recatalog.core;

import java.security.InvalidParameterException;
import java.util.Properties;

import org.antlr.v4.runtime.ParserRuleContext;

import br.com.recatalog.util.BicamSystem;
import br.com.recatalog.util.PropertyList;

public abstract class Symbol { 
	String name;
	Type   type;
	Language language;
	PropertyList properties;
	
	public Symbol(PropertyList properties) {
		this.properties = properties;
		
		name = (String) getProperty("NAME");
		if(name == null) {
			try {
				throw new InvalidParameterException();
			} catch(InvalidParameterException e) {
				BicamSystem.printLog("ERROR", "Invalid NULL property: 'name'");
			}
		}
		
		Scope scope = (Scope)getProperty("SCOPE");
		if(scope == null && !(name.equalsIgnoreCase("BUILTIN"))) {
			try {
				throw new NullPointerException();
			} catch(NullPointerException e) {
				BicamSystem.printLog("ERROR", "Invalid NULL parameter in Propertylist: 'SCOPE'");
			}
		}
		
		language = (Language) properties.getProperty("LANGUAGE");
		
		if(language == null) {
			try {
				throw new InvalidParameterException();
			} catch(InvalidParameterException e) {
				BicamSystem.printLog("ERROR", "Invalid NULL property: 'language'");
			}
		}
		
		if(properties.getProperty("CONTEXT") == null) {
			if(!properties.hasProperty("DEF_MODE", "BUILTIN")
					&& !properties.hasProperty("NAME", "GLOBAL")
				    && !properties.hasProperty("NAME", "BUILTIN")) {
				try {
					throw new InvalidParameterException();
				} catch(InvalidParameterException e) {
					BicamSystem.printLog("ERROR", "Invalid NULL property: 'context'");
				}
			}
		}
		this.properties = properties;
		init();
	}
	
	private void init() {
		setScope();
		setParent();
	}
	
	public String getName() {
		return name;
	}
	
	public String getId() {
		if(getScope() == null) {
			return getName();
		}
		else {
			Scope scope = getScope();
			if(OnlyScope.class.isInstance(scope)) return getName();
			else return  ((Symbol)scope).getId() + "." + getName();				
		}
	}
	
	public Type getType() {
		return type;
	}
	
	public void setType(Type type) {
		this.type = type;
		addProperty("TYPE", type);
	}
	
	public void addProperty(String key, Object value) {
		properties.addProperty(key, value);
	}
	
	public Object getProperty(String _key) {
		return properties.getProperty(_key);
	}
	
	public boolean hasProperty(String _key) {
		return properties.hasProperty(_key);
	}
	
	public boolean hasProperty(String _key, String _value) {
		return properties.hasProperty(_key, _value);
	}
	
	public PropertyList getProperties() {
		return properties;
	}
	
	public ParserRuleContext getContext() {
		return (ParserRuleContext)getProperty("CONTEXT");
	}
	
	public Scope getScope() {
		if(getParent() != null) return getParent();
		return (Scope)getProperty("SCOPE");
	}
	
	public Scope getParent() {
		return (Scope)getProperty("PARENT");
	}
	
	public void setScope() {
		Scope scope = (Scope)getProperty("SCOPE");

		if(scope == null && !(getName().equalsIgnoreCase("BUILTIN"))) {
			try {
				throw new NullPointerException();
			} catch(NullPointerException e) {
				BicamSystem.printLog("ERROR", "Invalid NULL parameter in Propertylist: 'SCOPE'");
			}
		}
		
		if(scope  != null) scope.addMember(this);
	}
	
	public void setParent() {
		Scope scope = (Scope)getProperty("PARENT");

		if(scope  != null) scope.addMember(this);
	}
	
	public int hashCode(){
		// if two objects have the same hashCode then equals() is called in Set Interface objects
		if(isCaseSensitive()) return getName().hashCode();
		else return getName().toUpperCase().hashCode();
	}
	
	public boolean equals(Object o){
        if (this == o) return true;
        
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        
		if(!(this.getClass().isInstance(o))) return false;
        
		if(this.hashCode() != o.hashCode()) return false;
		
//		Symbol other = (Symbol)o;
		
//		ParserRuleContext formalParamThis = NodeExplorer.getChildClass(getContext(), FormalParametersContext.class.getSimpleName());
//		ParserRuleContext formalParamOther = NodeExplorer.getChildClass(other.getContext(), FormalParametersContext.class.getSimpleName());
//
//		if(formalParamThis != null && formalParamOther != null) {
//			if(!(formalParamThis.getText().equals(formalParamOther.getText()))) return false;
//		}
		return true;
	}
	
	public boolean isCaseSensitive() {
		return language.isCaseSensitive();
	}
	
	public String location() {
		int line = getContext().getStart().getLine();
		int positionInLine = getContext().getStart().getCharPositionInLine();
		return String.format("Line: %d, Position in Line: %d.%n", line, positionInLine);
	}
	
	public String toStringAll() {
		return getName() + " - " + getClass().getName() + " - " + getClass().getSimpleName() + System.lineSeparator();	
	}
	
	public String toString() {
		if(getProperties().hasProperty("DEF_MODE", "BUILTIN")) return "";
		return "|.." + getName() + " - " + getClass().getSimpleName() + System.lineSeparator();	
	}
	
	public Properties serializeProperties() {
		Properties properties = new Properties();
		
		ParserRuleContext prctx = this.getContext();
		if(prctx != null)
			properties.put("CONTEXT", prctx.start.getLine() + ":" + prctx.start.getCharPositionInLine());
		else 
			properties.put("CONTEXT", "0:0");
		
		properties.put("TYPE", this.getClass().getSimpleName());
		properties.put("MODULE", (String)getProperties().getProperty("MODULE"));

		return properties;
	}	
}