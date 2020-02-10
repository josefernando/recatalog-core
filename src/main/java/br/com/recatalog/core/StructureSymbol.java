package br.com.recatalog.core;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.com.recatalog.util.PropertyList;

public  abstract class StructureSymbol extends ScopeSymbol implements Scope, Type { 
	Map<String,Set<Symbol>> members;       // scope

	public StructureSymbol(PropertyList properties) {
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

	public List<Symbol> getSymbolByProperties(PropertyList properties) {
		// TODO Auto-generated method stub
		return null;
	}
}