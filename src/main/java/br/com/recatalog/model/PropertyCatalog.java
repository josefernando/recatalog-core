package br.com.recatalog.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="TBPROPERTY_CATALOG")
public class PropertyCatalog implements Serializable {
	private static final long serialVersionUID = 1L;
		
	public PropertyCatalog() {}
	
	public PropertyCatalog(Catalog catalog, String key, String value) {
		
		this.propertyId = new PropertyId(catalog, key,  value);
	}

	@EmbeddedId
	private PropertyId propertyId;

	public PropertyId getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(PropertyId propertyId) {
		this.propertyId = propertyId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((propertyId == null) ? 0 : propertyId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PropertyCatalog other = (PropertyCatalog) obj;
		if (propertyId == null) {
			if (other.propertyId != null)
				return false;
		} else if (!propertyId.equals(other.propertyId))
			return false;
		return true;
	}
}

@Embeddable
class PropertyId implements Serializable  {  
	private static final long serialVersionUID = 1L;
	
	public PropertyId() {}
	
	public PropertyId(Catalog catalog, String key, String value) {
		this.catalog = catalog;
		this.key     = key;
		this.value   = value;
	}
	
	@ManyToOne
	@JoinColumn(name="FK_CATALOG_ID", referencedColumnName="ID")
	Catalog catalog;
	
	@Column(name="PROPERTY_KEY")
    String key;
	
	@Column(name="PROPERTY_VALUE")
    String value;	    
    
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	public Catalog getCatalog() {
		return catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}
}