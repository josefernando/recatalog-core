package br.com.recatalog.data;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.recatalog.model.BaseCatalog;
import br.com.recatalog.model.Catalog;
import br.com.recatalog.model.PropertyCatalog;
import br.com.recatalog.model.PropertyCatalog_;
import br.com.recatalog.model.SourceRepository;
import br.com.recatalog.util.PropertyList;

public class CatalogDAOHibernate implements CatalogDAO{
	
	@Override
	public PropertyList getCatalogById(PropertyList props) {
		
		EntityManagerFactory ENTITY_MANAGER_FACTORY =
				Persistence.createEntityManagerFactory("PU-DBRECATALOG");	
		
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		
		String id = (String)props.getProperty("ID");

		Catalog catalog = em.find(Catalog.class, id);
		
		props.addProperty("ENTITY", catalog);
		
		System.out.println(catalog.getId());
		
		em.close();
		
		System.err.println(catalog.getId() + " " + catalog.getName());
		
		ENTITY_MANAGER_FACTORY.close();		
		
		return props;
	}

	@Override
	public PropertyList getCataLogByParentId(PropertyList props) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PropertyList addCatalog(PropertyList properties) {
		
		EntityManagerFactory ENTITY_MANAGER_FACTORY =
				Persistence.createEntityManagerFactory("PU-DBRECATALOG");
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		
		Catalog catalog = (Catalog) properties.getProperty("ENTITY");

		em.getTransaction().begin();
		em.persist(catalog);
		
		System.err.println("SIZE: " + catalog.getProperties().size());
		
		for(PropertyCatalog pc : catalog.getProperties()) {
			em.persist(pc);
		}
		em.getTransaction().commit();
		System.out.println(catalog.getId());
		
		em.close();
		
		System.err.println(catalog.getId() + " " + catalog.getName());
		
		ENTITY_MANAGER_FACTORY.close();		

		return null;
	}

	@Override
	public PropertyList getCatalogAll(PropertyList props) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PropertyList addBaseCatalog(PropertyList properties) {
		
		EntityManagerFactory ENTITY_MANAGER_FACTORY =
				Persistence.createEntityManagerFactory("PU-DBRECATALOG");
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		
		BaseCatalog catalog = (BaseCatalog) properties.getProperty("ENTITY");

		em.getTransaction().begin();
		em.persist(catalog);
		
		System.err.println("SIZE: " + catalog.getProperties().size());
		
		for(PropertyCatalog_ pc : catalog.getProperties()) {
			em.persist(pc);
		}
		em.getTransaction().commit();
		System.out.println(catalog.getId());
		
		em.close();
		
		System.err.println(catalog.getId() + " " + catalog.getName());
		
		ENTITY_MANAGER_FACTORY.close();		

		return null;
	}
	
	@Override
	public PropertyList addSourceRepository(PropertyList properties) {
		
		EntityManagerFactory ENTITY_MANAGER_FACTORY =
				Persistence.createEntityManagerFactory("PU-DBRECATALOG");
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		
		SourceRepository catalog = (SourceRepository) properties.getProperty("ENTITY");

		em.getTransaction().begin();
		em.persist(catalog);
		
		System.err.println("SIZE: " + catalog.getProperties().size());
		
		for(PropertyCatalog_ pc : catalog.getProperties()) {
			em.persist(pc);
		}
		em.getTransaction().commit();
		System.out.println(catalog.getId());
		
		em.close();
		
		System.err.println(catalog.getId() + " " + catalog.getName());
		
		ENTITY_MANAGER_FACTORY.close();		

		return null;
	}
}