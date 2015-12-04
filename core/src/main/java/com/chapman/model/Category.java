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
		// TODO Auto-generated constructor stub
	}

	private Long categoryId;
	private String category;
	
	@OneToMany(mappedBy="category")
    private Set<RawBankCheckingData> employees;
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

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

}
