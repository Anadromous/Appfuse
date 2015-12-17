package com.chapman.webapp.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang.NumberUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.chapman.model.Category;

@FacesConverter("categoryConverter")
@ManagedBean
@RequestScoped
public class CategoryConverter implements Converter {

	protected final Log log = LogFactory.getLog(getClass());
	private List<Category> categories;

	/*
	 * @Override public String getAsString(FacesContext context, UIComponent
	 * component, Object value) {
	 * log.debug("Object from getAsString().............................."
	 * +value.toString()); Long id = (value instanceof Category) ? ((Category)
	 * value).getId() : null;
	 * log.debug("String from getAsString().............................."
	 * +String.valueOf(id)); return (id != null) ? String.valueOf(id) : null; }
	 */

	public CategoryConverter() {
		// TODO stubbing this for now. List should be obtained from the session,
		// request or another db call
		categories = new ArrayList<Category>();
		categories.add(new Category(1L, "Food"));
		categories.add(new Category(2L, "Gas"));
		categories.add(new Category(3L, "Entertainment"));
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		Iterator<Category> iterator = this.categories.iterator();
		while (iterator.hasNext()) {
			Category object = iterator.next();
			log.debug("Here is the category id and String id from CategoryConverter.getCategory(): "+ String.valueOf(object.getId()) + ", " + value);
			// return new Category(2L,"Gas");
			if (StringUtils.isNumeric(value)) {
				if (String.valueOf(object.getId()) == value || String.valueOf(object.getId()).equals(value)) {
					log.debug("Here is the object from a numeric: "+ object.toString());
					return object;
				} else {
					if (String.valueOf(object.getDescription()) == value || String.valueOf(object.getDescription()).equals(value)) {
						log.debug("Here is the object from a string: "+ object.toString());
						return object;
					}
				}
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		log.debug("Value from CategoryConverter.getAsString(): "+ value.toString());
		if (!(value instanceof Category)) {
			log.debug("Why are we returning null from CategoryConverter: "+ value.toString());
			return "3";
		}

		String s = String.valueOf(((Category) value).getId());
		log.debug("Here is the String from getAsString: " + s);
		return s;
	}

	public Category getCategory(String id) {
		Iterator<Category> iterator = this.categories.iterator();
		while (iterator.hasNext()) {
			Category object = iterator.next();
			log.debug("Here is the category id and String id from CategoryConverter.getCategory(): "+ String.valueOf(object.getId()) + ", " + id);
			// return new Category(2L,"Gas");
			if (String.valueOf(object.getId()) == id) {
				log.debug("Here is the object from CategoryConverter.getCategory(): "+ object.toString());
				return object;
			}
		}
		return null;
	}

}
