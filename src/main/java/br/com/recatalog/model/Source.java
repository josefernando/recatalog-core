package br.com.recatalog.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TBSOURCE")
public class Source extends CatalogItem {
	private static final long serialVersionUID = 1L;
}