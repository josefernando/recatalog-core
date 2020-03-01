package br.com.recatalog.data;

import br.com.recatalog.util.PropertyList;

public interface CatalogDAO {
	public PropertyList getCatalogById(PropertyList props);
	
	public PropertyList getCataLogByParentId(PropertyList props);
	
	public PropertyList addCatalog(PropertyList properties);
	
	public PropertyList addBaseCatalog(PropertyList properties);
	
	public PropertyList addSourceRepository(PropertyList properties);



	public PropertyList getCatalogAll(PropertyList props);
}