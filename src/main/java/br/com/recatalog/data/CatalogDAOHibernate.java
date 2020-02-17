package br.com.recatalog.data;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.recatalog.model.Catalog;
import br.com.recatalog.util.PropertyList;

public class CatalogDAOHibernate implements CatalogDAO{

	private static EntityManagerFactory ENTITY_MANAGER_FACTORY =
			Persistence.createEntityManagerFactory("PU-DBRECATALOG");	
	
	@Override
	public PropertyList getCatalogById(PropertyList props) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		
		String id = (String)props.getProperty("ID");

		Catalog catalog = em.find(Catalog.class, id);
		
		System.out.println(catalog.getId());
		
		em.close();
		
		System.err.println(catalog.getId() + " " + catalog.getName());
		
		ENTITY_MANAGER_FACTORY.close();		
		
		return null;
	}

	@Override
	public PropertyList getCataLogByParentId(PropertyList props) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PropertyList addCatalog(PropertyList p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PropertyList getCatalogAll(PropertyList props) {
		// TODO Auto-generated method stub
		return null;
	}
}