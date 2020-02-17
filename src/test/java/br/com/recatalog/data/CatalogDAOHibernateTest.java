package br.com.recatalog.data;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.recatalog.util.PropertyList;

public class CatalogDAOHibernateTest {
	
	PropertyList properties;
	
	@BeforeEach
	public void init() {
		properties = new PropertyList();
		properties.addProperty("ID", "SEGUROS");
	}
	
	@Test
	public void test() {
		
		CatalogDAOHibernate catalog = new CatalogDAOHibernate();
		
		PropertyList p = catalog.getCatalogById(properties);
		
		assertTrue(true);
	}
}