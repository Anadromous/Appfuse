package com.chapman.service.impl;

import com.chapman.dao.PersonDao;
import com.chapman.model.Person;
import com.chapman.service.PersonManager;
import com.chapman.service.impl.GenericManagerImpl;
 
import java.util.List;
 
public class PersonManagerImpl extends GenericManagerImpl<Person, Long> implements PersonManager {
    PersonDao personDao;
 
    public PersonManagerImpl(PersonDao personDao) {
        super(personDao);
        this.personDao = personDao;
    }
 
    public Person findByLastName(String lastName) {
        return personDao.findByLastName(lastName);
    }
}
