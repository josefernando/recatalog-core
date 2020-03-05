package br.com.recatalog.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TBDEPENDENCY")
public class Dependency extends CatalogItem {
	private static final long serialVersionUID = 1L;
}