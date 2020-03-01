package br.com.recatalog.data;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.recatalog.model.CatalogOld;
import br.com.recatalog.model.PropertyCatalogOld;

public class PropertyCatalogDAOHibernateTest {
	PropertyCatalogDAO propertyCatalogDAO;
	
	@BeforeEach
	public void init() {
		propertyCatalogDAO = new PropertyCatalogDAOHibernate();
	}
	
	@Test
	public void testAddPropertyCatalog() {
		CatalogOld catalog = new CatalogOld();
		catalog.setId("SEGUROS");
		
		PropertyCatalogOld propertyCatalog = new PropertyCatalogOld(catalog,"KEY01", "VALUE01");

		propertyCatalogDAO.addPropertyCatalog(propertyCatalog);
		assertTrue(true);
	}
}