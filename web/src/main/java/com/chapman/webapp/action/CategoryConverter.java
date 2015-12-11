package com.chapman.webapp.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.chapman.model.Category;

@FacesConverter("categoryConverter")
public class CategoryConverter implements Converter {
	
	protected final Log log = LogFactory.getLog(getClass());
	private List<Category> categories;

	public CategoryConverter() {
		// TODO stubbing this for now. List should be obtained from the session, request or another db call
		categories = new ArrayList<Category>();		
		categories.add(new Category(1L, "Food"));
		categories.add(new Category(2L, "Gas"));
		categories.add(new Category(3L, "Entertainment"));
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		log.debug("Here is the value from CategoryConverter.getAsObject(): "+value);
        return this.getCategory(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (!(value instanceof Category)) {
            return null;
        }

       String s =  String.valueOf(((Category) value).getCategoryId());
       log.debug("Here is the String from CategoryConverter: "+s);
       return s;
	}
	
	public Category getCategory(String id) {
		log.debug("Here is the id from CategoryConverter.getCategory(): "+id);
        Iterator<Category> iterator = this.categories.iterator();
        while(iterator.hasNext()) {
        	Category object = iterator.next();

            if(object.getCategoryId() == Long.valueOf(id).longValue()) {
                return object;
            }
        }
        return null;
    }

}
