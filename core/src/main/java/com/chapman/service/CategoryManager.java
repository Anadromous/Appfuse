package com.chapman.service;

import java.util.List;

import com.chapman.model.Category;

public interface CategoryManager extends GenericManager<Category, Long>{
	/**
     * {@inheritDoc}
     */
    List<Category> getCategories(Category category);

    /**
     * {@inheritDoc}
     */
    Category getCategory(String categoryName);

    /**
     * {@inheritDoc}
     */
    Category saveCategory(Category category);

    /**
     * {@inheritDoc}
     */
    void removeCategory(String categoryName);
}
