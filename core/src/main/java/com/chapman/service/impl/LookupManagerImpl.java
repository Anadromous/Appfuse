package com.chapman.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chapman.dao.LookupDao;
import com.chapman.dao.hibernate.LookupDaoHibernate;
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
    
    protected final Log log = LogFactory.getLog(getClass());
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
	public List<Category> getAllCategories() {
		return dao.getCategories();
	}
/*		List<Category> categories = dao.getCategories();
		List<LabelValue> list = new ArrayList<LabelValue>();
		for(Category category : categories){
			log.debug("Category from LookupManagerImpl........"+category.getCategoryId()+", "+category.getDescription());
			list.add(new LabelValue(category.getCategoryId().toString(), category.getDescription()));
		}
		return list;
	}*/
}
