package br.com.recatalog.core.visualbasic6;

import br.com.recatalog.core.Type;
import br.com.recatalog.util.PropertyList;

/**
 * 
 * @author josez
 * Type para meThods procedures get e set em classes
 */
public class MethodSymbol extends ScopeSymbol implements Type{

	public MethodSymbol(PropertyList _properties) {
		super(_properties);
	}
}