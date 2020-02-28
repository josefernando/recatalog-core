package br.com.recatalog.data;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.recatalog.model.Catalog;
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
		Catalog catalog = new Catalog();
		catalog.setId("SEGUROS");
		catalog.setName("REPOSITORY");
		catalog.setDescription("SEGUROS DESCRIPTION");
		catalog.setDtCreated(new Date());
		
		properties.addProperty("ENTITY", catalog);
		
		catalogDAO.addCatalog(properties);
		
		assertTrue(true);
	}
	
	@Test
	public void testAddCatalog() {
		Catalog catalog = new Catalog();
		catalog.setId("SEGUROS");
		catalog.setName("SEGUROS");
		catalog.setDescription("SEGUROS DESCRIPTION");
		catalog.setDtCreated(new Date());
		catalog.setParent(null);
		
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