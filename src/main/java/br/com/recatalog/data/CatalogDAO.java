package br.com.recatalog.data;

import br.com.recatalog.util.PropertyList;

public interface CatalogDAO {
	public PropertyList getCatalogById(PropertyList props);
	
	public PropertyList getCataLogByParentId(PropertyList props);
	
	public PropertyList addCatalog(PropertyList p);

	public PropertyList getCatalogAll(PropertyList props);
}