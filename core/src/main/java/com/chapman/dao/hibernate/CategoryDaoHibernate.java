/**
 * 
 */
package com.chapman.dao.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.chapman.dao.CategoryDao;
import com.chapman.model.Category;

/**
 * @author <a href="mailto:pchapman@easystreet.net">Peter Chapman</a>
 *
 */
public class CategoryDaoHibernate extends GenericDaoHibernate<Category, Long> implements CategoryDao {

	/**
	 * @param persistentClass
	 */
	public CategoryDaoHibernate() {
		super(Category.class);
	}

	/* (non-Javadoc)
	 * @see com.chapman.dao.CategoryDao#getCategoryByName(java.lang.String)
	 */
	@Override
	public Category getCategoryByName(String categoryName) {
		List<Category> categories = getSession().createCriteria(Category.class).add(Restrictions.eq("description", categoryName)).list();
        if (categories.isEmpty()) {
            return null;
        } else {
            return categories.get(0);
        }
	}

	/* (non-Javadoc)
	 * @see com.chapman.dao.CategoryDao#removeCategory(java.lang.String)
	 */
	@Override
	public void removeCategory(String categoryName) {
		Object category = getCategoryByName(categoryName);
        Session session = getSessionFactory().getCurrentSession();
        session.delete(category);
		
	}

}
