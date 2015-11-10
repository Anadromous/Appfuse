package com.chapman.service;
 
import com.chapman.service.GenericManager;
import com.chapman.model.Person;
 
import java.util.List;
 
public interface PersonManager extends GenericManager<Person, Long> {
    Person findByLastName(String lastName);
}