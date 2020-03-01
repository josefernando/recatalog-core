package br.com.recatalog.data;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.recatalog.model.Catalog;
import br.com.recatalog.model.PropertyCatalog;
import br.com.recatalog.util.PropertyList;

public class CatalogDAOHibernateTest {
	PropertyList properties;
	CatalogDAO catalogDAO;
	
	@BeforeEach
	public void init() {
		properties = new PropertyList();
		catalogDAO = new CatalogDAOHibernate();
	}
	
	@Test
	public void testGetCatalogById() {
		
		properties.clear();
		
		properties.addProperty("ID", "CAMBIO");
		properties = catalogDAO.getCatalogById(properties);
		
		Catalog catalog = (Catalog) properties.getProperty("ENTITY");
		
		PropertyCatalog pc = catalog.getProperties().get(0);
		
		System.err.println("key: " + pc.getKey() + " - value: " + pc.getValue());
		
		assertTrue(catalog != null);
	}
	
	@Test
	public void testAddCatalog() {
		Catalog catalog = new Catalog();
		catalog.setId("CAMBIO");
		catalog.setName("CAMBIO");
		catalog.setDescription("CAMBIO DESCRIPTION");
		catalog.setDtCreated(new Date());
		catalog.setParent(null);
		
		PropertyCatalog pc = new PropertyCatalog(catalog,"KEY002", "VALUE002");

		catalog.addProperty(pc);
		properties.clear();
		properties.addProperty("ENTITY", catalog);
		catalogDAO.addCatalog(properties);
		
//		
//		System.err.println(catalog.getProperties().isEmpty());
//		
//		Catalog catalog2 = new Catalog();
//		catalog2.setId("SEGUROS.REPOSITORY");
//		catalog2.setName("REPOSITORY");
//		catalog2.setDescription("REPOSITORY DESCRIPTION");
//		catalog2.setDtCreated(new Date());
//		catalog2.setParent(catalog);
//		
//		PropertyList pp = new PropertyList();
//		pp.addProperty("ENTITY", catalog2);
//		catalogDAO.addCatalog(pp);
		
		assertTrue(true);
	}
}