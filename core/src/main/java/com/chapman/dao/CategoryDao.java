/**
 * 
 */
package com.chapman.dao;

import com.chapman.model.Category;

/**
 * @author <a href="mailto:pchapman@easystreet.net">Peter Chapman</a>
 *
 */
public interface CategoryDao extends GenericDao<Category, Long> {
    /**
     * Gets category information based on categoryname
     * @param categoryName the categoryName
     * @return populated category object
     */
	Category getCategoryByName(String categoryName);

    /**
     * Removes a category from the database by name
     * @param categoryName the category's categoryName
     */
    void removeCategory(String categoryName);

}
