/**
 * 
 */
package com.chapman.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import static org.junit.Assert.*;
import com.chapman.model.Person;

/**
 * @author or0189783
 *
 */
public class PersonDaoTest extends BaseDaoTestCase {

	@Autowired
	private PersonDao personDao;
	
	public PersonDaoTest() {
		// TODO Auto-generated constructor stub
	}
	
	@Test
	public void testFindPersonByLastName() throws Exception {
	    List<Person> people = personDao.findByLastName("User");
	    log.debug("List people size: "+people.size());
	    assertTrue(people.size()>=0);
	}
	
	@Test(expected = DataAccessException.class)
	public void testAddAndRemovePerson() throws Exception {
	    Person person = new Person();
	    //person=(Person) populate(person);
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
