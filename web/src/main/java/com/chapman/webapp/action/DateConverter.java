package com.chapman.webapp.action;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@FacesConverter("dateConverter")
public class DateConverter implements Converter {

		public DateConverter() {
			// TODO Auto-generated constructor stub
		}

		protected final Log log = LogFactory.getLog(getClass());

     // The 'format' family of core Java classes are NOT thread-safe.
    // Each instance of this class needs its own DateFormat object or
    // runs the risk of two request threads accessing it at the same time.
    DateFormat df1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String stringValue) throws ConverterException {
        Date date = null;
        // Prevent ParseException when an empty form field is submitted
        // for conversion
        if (stringValue == null || stringValue.equals("")) {
            date = null;
        } else {
            try {
                date = df1.parse(stringValue);} catch (ParseException e) {
                    if (log.isDebugEnabled()) {
                        log.debug("Unable to convert string to Date object", e);
                    }
                    date = null;
                }
            }
            return date;
        }

        @Override
        public String getAsString(
                FacesContext context, 
                UIComponent component, 
                Object objectValue)
                throws ConverterException {
            if (objectValue == null) {
                return null;
            } else if (!(objectValue instanceof Date)) {
                throw new IllegalArgumentException(
                    "objectValue is not a Date object");
            } else {
                // Use 'toUpperCase()' to fix mixed case string returned
                // from 'MMM' portion of date format
                return df1.format(objectValue).toUpperCase();
            }
        }

    }