package com.chapman.webapp.action;

import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.chapman.Constants;
import com.chapman.model.Category;
import com.chapman.model.LabelValue;

@FacesConverter("categoryConverter")
@ManagedBean
@RequestScoped
public class CategoryConverter extends BasePage implements Converter {

	protected final Log log = LogFactory.getLog(getClass());
	List<LabelValue> categorLabels = (List) getServletContext().getAttribute(Constants.CATEGORIES);

	public CategoryConverter() {
		
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		Iterator<LabelValue> iterator = this.categorLabels.iterator();
		while (iterator.hasNext()) {
			LabelValue object = iterator.next();
			log.debug("Here is the category id and String id from CategoryConverter.getCategory(): "+ object.getLabel() + ", " + value);
			if (StringUtils.isNumeric(value)) {
				if (String.valueOf(object.getLabel()) == value || String.valueOf(object.getLabel()).equals(value)) {
					log.debug("Here is the object from a numeric: "+ object.toString());
					return new Category(Long.valueOf(object.getLabel()),object.getValue());
				} else {
					if (String.valueOf(object.getValue()) == value || String.valueOf(object.getValue()).equals(value)) {
						log.debug("Here is the object from a string: "+ object.toString());
						return new Category(Long.valueOf(object.getLabel()),object.getValue());
					}
				}
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		log.debug("Value from CategoryConverter.getAsString(): "+ value.toString());
		if (value instanceof Category) {
			log.debug("Category object from CategoryConverter: "+ value.toString());
			return String.valueOf(((Category) value).getId());
		}else{
			log.debug("String object from CategoryConverter: "+ value.toString());
			return (String)value;
		}
	}
}
