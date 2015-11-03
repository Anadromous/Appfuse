/**
 * 
 */
package com.chapman.service;
 
import java.util.List;
import com.chapman.model.Person;
import javax.jws.WebService;
/**
 * @author PMC
 *
 */
@Service
public interface PersonManager extends GenericManager<Person, Long> {

    List<Person> findByLastName(@PathParam("lastname") String lastName);

    List<Person> getPeople();
}
