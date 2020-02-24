package br.com.recatalog.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

//@Entity
@Embeddable
@Table(name="TBPROPERTY_CATALOG")
public class PropertyCatalog implements Serializable {
	private static final long serialVersionUID = 1L;
		
	public PropertyCatalog() {}
	
	public PropertyCatalog(String key, String value) {
//		this.parent = parent;
		this.key = key;
		this.value = value;
	}

//	@ManyToOne()
//	@JoinColumn(name = "FK_CATALOG_ID")
//	@Transient
//	private Catalog parent;

//	@Id
	@Column(name="PROPERTY_KEY") 
	private String key;
	
//	@Id
	@Column(name="PROPERTY_VALUE")
	private String value;

//	public Catalog getParent() {
//		return parent;
//	}
//
//	public void setParent(Catalog parent) {
//		this.parent = parent;
//	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
}

//	class PropertyId implements Serializable  {  // multifield primary key
//		/**
//		 * 
//		 */
//		private static final long serialVersionUID = 1L;
//		
////		@OneToOne
////	    @JoinColumn(name = "CATALOG_ID")
////		Catalog parent;
//		
//		@Column(name="PROPERTY_KEY")
//	    String key;
//		
//		@Column(name="PROPERTY_VALUE")
//	    String value;	    
//	    
//		public String getKey() {
//			return key;
//		}
//		public void setKey(String key) {
//			this.key = key;
//		}
//		public String getValue() {
//			return value;
//		}
//		public void setValue(String value) {
//			this.value = value;
//		}
//		@Override
//		public int hashCode() {
//			final int prime = 31;
//			int result = 1;
//			result = prime * result + ((key == null) ? 0 : key.hashCode());
//			result = prime * result + ((value == null) ? 0 : value.hashCode());
//			return result;
//		}
//		@Override
//		public boolean equals(Object obj) {
//			if (this == obj)
//				return true;
//			if (obj == null)
//				return false;
//			if (getClass() != obj.getClass())
//				return false;
//			PropertyId other = (PropertyId) obj;
//			if (key == null) {
//				if (other.key != null)
//					return false;
//			} else if (!key.equals(other.key))
//				return false;
//			if (value == null) {
//				if (other.value != null)
//					return false;
//			} else if (!value.equals(other.value))
//				return false;
//			return true;
//		}		
//	}