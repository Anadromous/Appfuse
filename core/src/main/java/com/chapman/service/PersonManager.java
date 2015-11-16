package com.chapman.service;
 
import java.util.List;

import javax.jws.WebService;
import com.chapman.service.GenericManager;
import com.chapman.model.Person;
 
@WebService
public interface PersonManager extends GenericManager<Person, Long> {
	
    List<Person> findByLastName(String lastName);
}