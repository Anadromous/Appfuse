package com.chapman.dao;

import com.chapman.dao.GenericDao;
import com.chapman.model.Person;
 
import java.util.List;
 
public interface PersonDao extends GenericDao<Person, Long> {
    public List<Person> findByLastName(String lastName);
}
