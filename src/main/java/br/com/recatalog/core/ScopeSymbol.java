package br.com.recatalog.core;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import br.com.recatalog.util.PropertyList;

public  abstract class ScopeSymbol extends Symbol implements Scope, Type { 
	protected Map<String,Set<Symbol>> members;       // scope

	public ScopeSymbol(PropertyList properties) {
		super(properties);
		members  = new LinkedHashMap<String,Set<Symbol>>();
	}

	public Map<String,Set<Symbol>> getMembers(){
		return members;
	} 
	
	public int hashCode(){
		// if two objects have the same hashCode then equals() is called in Set Interface objects
		if(isCaseSensitive()) return getName().hashCode();
		else return getName().toUpperCase().hashCode();
	}
	public abstract String toString();
	
//	public String toString() {
//		StringBuilder sb = new StringBuilder();
//		if(getMembers().size() > 0) {
//			if(TypeSymbol.class.isInstance(this) || EnumSymbol.class.isInstance(this))
//				sb.append("|---> Parent: " + getName() + " - " + getClass().getSimpleName() + System.lineSeparator());
//			else 
//				sb.append("|-->> Scope: " + getName() + " - " + getClass().getSimpleName() + System.lineSeparator());
//
//			Iterator<Entry<String, Set<Symbol>>> it = getMembers().entrySet().iterator();
//			
//			for(Entry<String, Set<Symbol>> entry : getMembers().entrySet()) {
//				for(Symbol sym : entry.getValue()) {
////					if(sym.getProperties().hasProperty("DEF_MODE","BUILTIN")) continue;
//					/**
//					 * Entendendo "((?im)^)", ".."
//					 * Inclui identação ".." em cada linha de sb
//					 */
//					sb.append(sym.toString().replaceAll("((?im)^)", ".."));
//				}
//			}
//		}
//		else {
//			sb.append("|.." + getName() + " - " + getClass().getSimpleName() + System.lineSeparator());
//		}
//		return sb.toString();
//	}
	public abstract String toStringAll();

//	
//	public String toStringAll() {
//		StringBuilder sb = new StringBuilder();
//		if(getMembers().size() > 0) {
//			if(TypeSymbol.class.isInstance(this))
//				sb.append("|..-> Parent: " + getName() + " - " + getClass().getSimpleName() + System.lineSeparator());
//			else
//				sb.append("|..-> Scope: " + getName() + " - " + getClass().getSimpleName() + System.lineSeparator());
//			for(Entry<String, Set<Symbol>> entry : getMembers().entrySet()) {
//				for(Symbol sym : entry.getValue()) {
//					/**
//					 * Entendendo "((?im)^)", ".."
//					 * Inclui ".." em cada linha de sb
//					 */
//					sb.append(sym.toString().replaceAll("((?im)^)", ".."));
//				}
//			}
//		}
//		else {
//			sb.append("|.." + getName() + " - " + getClass().getSimpleName() + System.lineSeparator());
//		}
//		return sb.toString();
//	}
	
	public String toString(String name) {
		if(getName().equals(name))	return getProperties().toString();	
		return null;
	}
}