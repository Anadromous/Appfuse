/**
  *
  */
package com.chapman.service;

import java.util.List;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;


import javax.ws.rs.PathParam;

import org.springframework.stereotype.Service;

import com.chapman.model.Person;

@WebService
@Path("/people") //for REST
public interface PersonManager extends GenericManager<Person, Long> {

     @GET
     @Path("{lastname}")
     List<Person> findByLastName(@PathParam("lastname") String lastName);

     @GET
     List<Person> getPeople();
}