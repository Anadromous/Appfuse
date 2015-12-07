package com.chapman.model;

import java.io.Serializable;
import java.util.Set;

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
		this.category=description;
	}

	private Long categoryId;
	private String category;
	
	@OneToMany(mappedBy="category")
    private Set<RawBankCheckingData> categories;
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
	@Column(name = "category", nullable = false, length = 50)
	@Field
	public String getCategory() {
		return category;
	}

	/**
	 * @param description the description to set
	 */
	public void setCategory(String description) {
		this.category = description;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Category [category=" + category + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((category == null) ? 0 : category.hashCode());
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
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		return true;
	}



}
