package com.chapman.service;
 
import java.util.List;

import javax.jws.WebService;

import com.chapman.service.GenericManager;
import com.chapman.model.Person;
 
//@WebService
public interface PersonManager extends GenericManager<Person, Long> {
	
    /**
     * Removes a person from the database
     *
     * @param person the person to remove
     */
    void removePerson(Person person);
    List<Person> getPeople();
    List<Person> findByLastName(String lastName);
}