package com.chapman.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.chapman.model.Person;
import com.chapman.model.User;

public class PersonDaoTest extends BaseDaoTestCase {
    @Autowired
    private PersonDao personDao;

/*    @Test
    public void testFindPersonByLastName() throws Exception {
        Person person = personDao.findByLastName("Raible");
        assertEquals(person.getLastName(), "Raible");
    }*/

    @Test(expected=DataAccessException.class)
    public void testAddAndRemovePerson() throws Exception {
        Person person = new Person();
        person.setFirstName("Country");
        person.setLastName("Bry");

        person = personDao.save(person);
        flush();

        person = personDao.get(person.getId());

        assertEquals("Country", person.getFirstName());
        assertNotNull(person.getId());

        log.debug("removing person...");

        personDao.remove(person.getId());
        flush();

        // should throw DataAccessException
        personDao.get(person.getId());
    }
}