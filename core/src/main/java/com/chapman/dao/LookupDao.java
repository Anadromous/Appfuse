package com.chapman.dao;

import java.util.List;

import com.chapman.model.Category;
import com.chapman.model.Role;

/**
 * Lookup Data Access Object (GenericDao) interface.  This is used to lookup values in
 * the database (i.e. for drop-downs).
 *
 * @author <a href="mailto:pchapman@easystreet.net">Peter Chapman</a>
 */
public interface LookupDao {
    //~ Methods ================================================================

    /**
     * Returns all Roles ordered by name
     * @return populated list of roles
     */
    List<Role> getRoles();
    
    /**
     * Returns all Categories ordered by description
     * @return populated list of categories
     */
    List<Category> getCategories();
}
