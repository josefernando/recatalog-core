package br.com.recatalog.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TBRUNTIME_DEPENDENCY")
public class RunTimeDependency extends CatalogItem {
	private static final long serialVersionUID = 1L;
}