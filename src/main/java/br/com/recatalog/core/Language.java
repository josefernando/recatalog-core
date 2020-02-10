package br.com.recatalog.core;

import java.security.InvalidParameterException;
import java.util.LinkedHashMap;
import java.util.Map;

import br.com.recatalog.util.BicamSystem;
import br.com.recatalog.util.PropertyList;

public abstract class Language { 
	PropertyList properties;
	protected Map<String,PropertyList> preDefinedSymbols;

	public abstract boolean isCaseSensitive();
	
	public String getName() {
		return (String)getProperties().getProperty("NAME");
	}
	
	public Language() {
		this.properties = new PropertyList();
	    this.preDefinedSymbols = new LinkedHashMap <>();
		this.properties = new PropertyList();

		setProperties();
		validationProperties();
	}
	
	public PropertyList getProperties() {
		return properties;
	}
	
	public abstract void setProperties();
	
	public void validationProperties() {
		String name = (String) getProperties().getProperty("NAME");
		if(name == null) {
			try {
				throw new InvalidParameterException();
			} catch(InvalidParameterException e) {
				BicamSystem.printLog("ERROR", "Invalid NULL property: 'NAME'");
			}
		}
		Boolean caseSensitive = (Boolean) getProperties().getProperty("CASE_SENSITIVE");
		if(caseSensitive == null) {
			try {
				throw new InvalidParameterException();
			} catch(InvalidParameterException e) {
				BicamSystem.printLog("ERROR", "Invalid NULL property: 'CASE_SENSITIVE'");
			}
		}
	}
	 
	public Map<String,PropertyList> getPreDefinedSymbols(){
		return preDefinedSymbols;
	}	
	 
	 public String toString() {
		 return getName();
	 }
	 
	 public String toString(String name) {
		 return getProperties().toString();
	 }
}