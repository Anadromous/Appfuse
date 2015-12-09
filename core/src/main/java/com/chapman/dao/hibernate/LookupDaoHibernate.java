package com.chapman.dao.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.chapman.dao.LookupDao;
import com.chapman.model.Category;
import com.chapman.model.Role;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.hibernate.Session;

/**
 * Hibernate implementation of LookupDao.
 *
 * @author <a href="mailto:pchapman@easystreet.net">Peter Chapman</a>
 *      Modified by jgarcia: updated to hibernate 4
 */
@Repository
public class LookupDaoHibernate implements LookupDao {
    private Log log = LogFactory.getLog(LookupDaoHibernate.class);
    private final SessionFactory sessionFactory;

    /**
     * Initialize LookupDaoHibernate with Hibernate SessionFactory.
     * @param sessionFactory
     */
    @Autowired
    public LookupDaoHibernate(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Role> getRoles() {
        log.debug("Retrieving all role names...");
        Session session = sessionFactory.getCurrentSession();
        List<Role> roles = session.createCriteria(Role.class).list();
        log.debug("roles.size()..........................."+roles.size());
        return roles;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
	public List<Category> getCategories() {
		log.debug("Retrieving categories..........................");
		Session session = sessionFactory.getCurrentSession();
		List<Category> categories = session.createCriteria(Category.class).list();
		log.debug("categories.size()..........................."+categories.size());
		return categories;
	}
}
