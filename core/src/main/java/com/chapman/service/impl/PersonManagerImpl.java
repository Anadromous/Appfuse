package com.chapman.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chapman.dao.PersonDao;
import com.chapman.model.Person;
import com.chapman.service.PersonManager;

@Service("personManager")
//@WebService(serviceName = "PersonService", endpointInterface = "com.chapman.service.PersonManager")
public class PersonManagerImpl extends GenericManagerImpl<Person, Long> implements PersonManager {
	PersonDao personDao;

	public PersonManagerImpl(PersonDao personDao) {
		super(personDao);
		this.personDao = personDao;
	}

	public List<Person> findByLastName(String lastName) {
		return personDao.findByLastName(lastName);
	}
}
