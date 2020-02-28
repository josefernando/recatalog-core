package br.com.recatalog.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="TBCATALOG")
public class Catalog implements Serializable{
	/** 
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Catalog() {
		properties = new ArrayList<PropertyCatalog>();
	}
	
	@Id
	@Column(name="ID", unique = true)
	private String id;
	
	@Column(name="DT_CREATED", nullable=false)
	private Date dtCreated;
	
	@Column(name="NAME", nullable=false)
    private String name;
    
	@Column(name="DESCRIPTION", nullable=false)
    private String description;
    
	@OneToOne()  // foreign key  
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
	private Catalog parent;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy="propertyId.catalog")
//	@JoinColumn(name = "catalog")
	private List<PropertyCatalog> properties;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getDtCreated() {
		return dtCreated;
	}
	public void setDtCreated(Date dtCreated) {
		this.dtCreated = dtCreated;
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
	public Catalog getParent() {
		return parent;
	}
	public void setParent(Catalog parent) {
		this.parent = parent;
	}

	public List<PropertyCatalog> getProperties() {
		return properties;
	}
	public void setProperties(List<PropertyCatalog> properties) {
		this.properties = properties;
	}
	
	public void addProperty(String key, String value) {
		PropertyCatalog property = new PropertyCatalog(this, key,value);
		properties.add(property);
	}
	
	public void addProperty(PropertyCatalog propertyCatalog) {
		properties.add(propertyCatalog);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Catalog other = (Catalog) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
}