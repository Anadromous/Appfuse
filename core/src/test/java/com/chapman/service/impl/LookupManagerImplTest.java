package com.chapman.service.impl;

import com.chapman.Constants;
import com.chapman.dao.LookupDao;
import com.chapman.model.Category;
import com.chapman.model.LabelValue;
import com.chapman.model.Role;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;


public class LookupManagerImplTest extends BaseManagerMockTestCase {

    @Mock
    private LookupDao lookupDao;

    @InjectMocks
    private LookupManagerImpl mgr = new LookupManagerImpl();


    @Test
    public void testGetAllRoles() {
        log.debug("entered 'testGetAllRoles' method");

        //given
        Role role = new Role(Constants.ADMIN_ROLE);
        final List<Role> testData = new ArrayList<Role>();
        testData.add(role);

        given(lookupDao.getRoles()).willReturn(testData);

        //when
        List<LabelValue> roles = mgr.getAllRoles();

        //then
        assertTrue(roles.size() > 0);
    }
    
    @Test
    public void testGetCategories() throws Exception{
    	log.debug("testGetCategories.............................");
    	Category c = new Category();
    	c.setId(-1L);
    	c.setDescription("Food");
    	final List<Category> test = new ArrayList<Category>();
    	test.add(c);
    	
    	given(lookupDao.getCategories()).willReturn(test);
    	
    	List<LabelValue> categories = mgr.getAllCategories();
    	assertTrue(categories.size() > 0);
    }

}
