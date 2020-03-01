package br.com.recatalog.data;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.recatalog.model.PropertyCatalogOld;

public class PropertyCatalogDAOHibernate implements PropertyCatalogDAO{

	@Override
	public void addPropertyCatalog(PropertyCatalogOld propertyCatalog) {
		EntityManagerFactory ENTITY_MANAGER_FACTORY =
				Persistence.createEntityManagerFactory("PU-DBRECATALOG");
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

		em.getTransaction().begin();
		em.persist(propertyCatalog);
		em.getTransaction().commit();
		System.out.println(propertyCatalog);
		em.close();
		ENTITY_MANAGER_FACTORY.close();		
	}
}