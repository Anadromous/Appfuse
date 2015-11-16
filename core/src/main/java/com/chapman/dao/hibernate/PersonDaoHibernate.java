/**
 * 
 */
package com.chapman.dao.hibernate;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.chapman.dao.PersonDao;
import com.chapman.model.Person;
import com.chapman.model.User;

/**
 * @author or0189783
 *
 */
@Repository("personDao")
public class PersonDaoHibernate extends GenericDaoHibernate<Person, Long> implements PersonDao {

    public PersonDaoHibernate() {
        super(Person.class);
    }

    public List<Person> findByLastName(String lastName) throws UsernameNotFoundException {
        /*List<Person> persons=getSession().createCriteria(Person.class).add(Restrictions.eq("lastName", lastName)).list();
        log.debug("Person to find: "+lastName);
        if (persons == null || persons.isEmpty()) {
            throw new UsernameNotFoundException("person '" + lastName + "' not found...");
        } else {
            return (Person)persons.get(0);
        }*/
    	return getSession().createCriteria(Person.class).add(Restrictions.eq("lastName", lastName)).list();
    }
}