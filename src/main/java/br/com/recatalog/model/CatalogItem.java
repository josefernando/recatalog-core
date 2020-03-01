package br.com.recatalog.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TBCATALOG_ITEM")
@Inheritance(strategy = InheritanceType.JOINED)
public class CatalogItem implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public CatalogItem() {
		properties = new ArrayList<PropertyCatalog_>();
	}

	@Id
	@Column(name="ID", unique = true)
	private String id;

	@Column(name="CREATED_ON", nullable=false)
	private Date createdOn;
	
	@Column(name="NAME", nullable=false)
    private String name;
    
	@Column(name="DESCRIPTION", nullable=false)
    private String description;
    
	@OneToOne()  // foreign key  
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
	private CatalogItem parent;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy="propertyId.catalog")
	private List<PropertyCatalog_> properties;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setDtCreated(Date createdOn) {
		this.createdOn = createdOn;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public CatalogItem getParent() {
		return parent;
	}
	public void setParent(CatalogItem parent) {
		this.parent = parent;
	}

	public List<PropertyCatalog_> getProperties() {
		return properties;
	}
	public void setProperties(List<PropertyCatalog_> properties) {
		this.properties = properties;
	}
	
	public void addProperty(String key, String value) {
		PropertyCatalog_ property = new PropertyCatalog_(this, key,value);
		properties.add(property);
	}
	
	public void addProperty(PropertyCatalog_ propertyCatalog) {
		properties.add(propertyCatalog);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		CatalogItem other = (CatalogItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}