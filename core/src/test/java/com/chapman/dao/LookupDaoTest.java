package com.chapman.dao;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.chapman.model.Category;
import com.chapman.model.Role;

/**
 * This class tests the current LookupDao implementation class
 * @author mraible
 */
public class LookupDaoTest extends BaseDaoTestCase {
    @Autowired
    LookupDao lookupDao;

    @Test
    public void testGetRoles() {
        List<Role> roles = lookupDao.getRoles();
        log.debug(roles);
        assertTrue(roles.size() > 0);
    }
    
    @Test
    public void testGetCategories() throws Exception{
    	List<Category> categories = lookupDao.getCategories();
    	assertTrue(categories.size() > 2);
    }
}
