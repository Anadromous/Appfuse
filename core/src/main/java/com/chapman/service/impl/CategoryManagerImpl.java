/**
 * 
 */
package com.chapman.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.chapman.dao.CategoryDao;
import com.chapman.model.Category;
import com.chapman.service.CategoryManager;

/**
 * @author <a href="mailto:pchapman@easystreet.net">Peter Chapman</a>
 *
 */
public class CategoryManagerImpl extends GenericManagerImpl<Category, Long> implements CategoryManager {
	
	CategoryDao dao;
	
	@Autowired
	public CategoryManagerImpl(CategoryDao categoryDao) {
		super(categoryDao);
		this.dao = categoryDao;
	}

	@Override
	public List<Category> getAll() {
		return dao.getAll();
	}

	@Override
	public List<Category> getCategories(Category category) {
		return dao.getAll();
	}

	@Override
	public Category getCategory(String categoryName) {
		return dao.getCategoryByName(categoryName);
	}

	@Override
	public Category saveCategory(Category category) {
		return dao.save(category);
	}

	@Override
	public void removeCategory(String categoryName) {
		dao.removeCategory(categoryName);
		
	}

}
