/**
 * 
 */
package com.chapman.service;
 
import java.util.List;

import javax.ws.rs.PathParam;

import org.springframework.stereotype.Service;

import com.chapman.model.Person;
/**
 * @author PMC
 *
 */
@Service
public interface PersonManager extends GenericManager<Person, Long> {

    List<Person> findByLastName(@PathParam("lastname") String lastName);

    //List<Person> getPeople();
}
