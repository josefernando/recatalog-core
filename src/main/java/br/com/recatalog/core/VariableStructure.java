package br.com.recatalog.core;

import br.com.recatalog.util.PropertyList;

public abstract class VariableStructure  extends ScopeSymbol implements Scope {
	public VariableStructure(PropertyList properties) {
		super(properties);
	}
}
