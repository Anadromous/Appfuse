package com.chapman.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chapman.dao.LookupDao;
import com.chapman.model.Category;
import com.chapman.model.LabelValue;
import com.chapman.model.Role;
import com.chapman.service.LookupManager;


/**
 * Implementation of LookupManager interface to talk to the persistence layer.
 *
 * @author <a href="mailto:pchapman@easystreet.net">Peter Chapman</a>
 */
@Service("lookupManager")
public class LookupManagerImpl implements LookupManager {
    @Autowired
    LookupDao dao;

    /**
     * {@inheritDoc}
     */
    public List<LabelValue> getAllRoles() {
        List<Role> roles = dao.getRoles();
        List<LabelValue> list = new ArrayList<LabelValue>();

        for (Role role1 : roles) {
            list.add(new LabelValue(role1.getName(), role1.getName()));
        }
        return list;
    }

    /**
     * {@inheritDoc}
     */
	@Override
	public List<LabelValue> getAllCategories() {
		List<Category> categories = dao.getCategories();
		System.out.println("...............size: "+categories.size());
		List<LabelValue> list = new ArrayList<LabelValue>();
		for(Category category : categories){
			System.out.println("Category........"+category.getCategoryId()+", "+category.getCategory());
			list.add(new LabelValue(category.getCategoryId().toString(), category.getCategory()));
		}
		return list;
	}
}
