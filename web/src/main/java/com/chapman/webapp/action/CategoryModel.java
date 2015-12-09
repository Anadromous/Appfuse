/**
 * 
 */
package com.chapman.webapp.action;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.chapman.model.LabelValue;
import com.chapman.service.LookupManager;
import com.chapman.service.impl.LookupManagerImpl;

/**
 * @author OR0189783
 *
 */
public class CategoryModel {

	public CategoryModel() {
	}

	LookupManager lookup = new LookupManagerImpl();

	public Map<String, String> getCategories() {
		System.out.println("+++++++++++++++++++++++"+lookup.toString());
		List<LabelValue> categoryList = lookup.getAllCategories();
		Map<String, String> categories = new LinkedHashMap<String, String>();
		// loop through and convert list to a JSF-Friendly Map for a <select>
		for (Object category : categoryList) {
			LabelValue option = (LabelValue) category;
			if (!categories.containsValue(option.getValue())) {
				System.out.println("categories................ "+option.getValue());
				categories.put(option.getLabel(), option.getValue());
			}
		}
		return categories;
	}
}
