package br.com.recatalog.data;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.recatalog.model.Catalog;
import br.com.recatalog.model.PropertyCatalog;

public class PropertyCatalogDAOHibernateTest {
	PropertyCatalogDAO propertyCatalogDAO;
	
	@BeforeEach
	public void init() {
		propertyCatalogDAO = new PropertyCatalogDAOHibernate();
	}
	
	@Test
	public void testAddPropertyCatalog() {
		Catalog catalog = new Catalog();
		catalog.setId("SEGUROS");
		
		PropertyCatalog propertyCatalog = new PropertyCatalog(catalog,"KEY01", "VALUE01");

		propertyCatalogDAO.addPropertyCatalog(propertyCatalog);
		assertTrue(true);
	}
}