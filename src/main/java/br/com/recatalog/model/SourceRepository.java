package br.com.recatalog.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TBSOURCE_REPOSITORY")
public class SourceRepository extends CatalogItem {
	private static final long serialVersionUID = 1L;
}