/**
 * 
 */
package com.chapman.webapp.action;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.chapman.model.Category;
import com.chapman.model.LabelValue;
import com.chapman.service.LookupManager;
import com.chapman.service.impl.LookupManagerImpl;

/**
 * @author OR0189783
 *
 */
public class CategoryModel {

	protected final Log log = LogFactory.getLog(getClass());
	
	public CategoryModel() {
	}

	LookupManager lookup = new LookupManagerImpl();

	public Map<String, String> getCategories() {
		List<Category> categoryList = lookup.getAllCategories();
		log.debug("categories size from CategoryModel................ "+categoryList.size());
		Map<String, String> categories = new LinkedHashMap<String, String>();
		// loop through and convert list to a JSF-Friendly Map for a <select>
		for (Object category : categoryList) {
			LabelValue option = (LabelValue) category;
			if (!categories.containsValue(option.getValue())) {
				log.debug("categories................ "+option.getValue());
				categories.put(option.getLabel(), option.getValue());
			}
		}
		return categories;
	}
}
