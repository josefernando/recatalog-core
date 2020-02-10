package br.com.recatalog.core.visualbasic6;

import br.com.recatalog.core.Type;
import br.com.recatalog.util.PropertyList;

public class ClassSymbol extends StructureSymbol implements Type {
	
	ClassSymbol superClass;

	public ClassSymbol(PropertyList properties) {
		super(properties);
		ClassSymbol superClass = (ClassSymbol) properties.getProperty("SUPER_CLASS");
	}
	
	public ClassSymbol getSuperClass() {
		return superClass;
	}	
}
