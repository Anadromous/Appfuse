package com.chapman.dao;
 
import java.util.List;

import com.chapman.model.Person;
 
public interface PersonDao extends GenericDao<Person, Long> {
	
    public List<Person> findByLastName(String lastName);
}