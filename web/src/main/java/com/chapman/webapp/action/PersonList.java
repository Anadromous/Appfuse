package com.chapman.webapp.action;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.chapman.model.Person;
import com.chapman.service.GenericManager;

public class PersonList extends BasePage implements Serializable {

	private static final long serialVersionUID = 1L;
	private GenericManager<Person, Long> personManager;
	 
    @Autowired
    public void setPersonManager(@Qualifier("personManager") GenericManager<Person, Long> personManager) {
        this.personManager = personManager;
    }
 
    public PersonList() {
        setSortColumn("id"); // sets the default sort column
    }
 
    public List<Person> getPersons() {
        return sort(personManager.getAll());
    }

}
