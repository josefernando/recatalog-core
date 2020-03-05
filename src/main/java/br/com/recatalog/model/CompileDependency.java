package br.com.recatalog.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TBCOMPILE_DEPENDENCY")
public class CompileDependency extends CatalogItem {
	private static final long serialVersionUID = 1L;
}