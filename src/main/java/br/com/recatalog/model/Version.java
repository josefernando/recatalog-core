package br.com.recatalog.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TBVERSION")
public class Version extends CatalogItem {
	private static final long serialVersionUID = 1L;
}