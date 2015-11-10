package com.chapman.dao;
 
import com.chapman.model.Person;
import com.chapman.model.User;
 
public interface PersonDao extends GenericDao<Person, Long> {
    public Person findByLastName(String lastName);
}