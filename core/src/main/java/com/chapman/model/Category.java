package com.chapman.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

@Entity
@Table(name = "category")
@Indexed
public class Category extends BaseObject implements Serializable {

	private static final long serialVersionUID = 1L;

	public Category() {
	}
	
	public Category(Long id, String description){
		this.categoryId=id;
		this.description=description;
	}

	private Long categoryId;
	private String description;
	
	@OneToMany(mappedBy="category")
    private List<RawBankCheckingData> categories;
	
	/**
	 * @return the id
	 */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @DocumentId
	public Long getCategoryId() {
		return categoryId;
	}

	/**
	 * @param id the id to set
	 */
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	
	/**
	 * @return the description
	 */
	@Column(name = "description", nullable = false, length = 50)
	@Field
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Category [description=" + description + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		return true;
	}



}
