package com.controller;

import com.exception.PersonNotFoundException;
import com.model.Person;
import com.service.PersonService;
import com.service.PersonServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/personService")
public class PersonController {
    PersonService personService = new PersonServiceImpl();

    @POST
    @Path("/personCrud")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response savePerson(Person person) {
        try {
            personService.createPerson(person);
            return Response.status(201).entity(person.toString() + "\n bilgilere sahip bir person eklenmiştir.").build();
        } catch (Exception e) {
            return Response.status(501).entity(person.toString() + "\n bilgilere sahip bir person eklenememiştir.").build();
        }
    }
    //POST  olarak http://localhost:8090/msg/personService/personCrud
    // Postmanda body kısmında
    //Json ise ornekteki gibi doldurulacaktır
    // {"id":321,
    // "name":"Muhammed",
    // "surname":"Demir"}


    @GET
    @Path("/personCrud")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonById(@QueryParam("personId") int personId) throws PersonNotFoundException {
        Person personList = null;

            personList = personService.findPerson(personId);
        if (personList.getName() != null)
            return Response.status(200).entity(personList).build();
        else
            throw new PersonNotFoundException(personId + " numaralı Person bulunamadı");
        //  return Response.status(501).entity(personId + " numaralı Person bulunamadı").build();

    }
    //GET  olarak http://localhost:8090/msg/personService/personCrud?personId=4

    @PUT
    @Path("/personCrud")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePerson(Person person) {
        try {
            personService.updatePerson(person);
            return Response.status(200).entity("personId= " + person.getId() + "\n personName= " + person.getName() + "\n personSurname =" + person.getSurname() + "\n bilgilere sahip bir person güncellenmistir.").build();
        } catch (Exception e) {
            return Response.status(501).entity("personId= " + person.getId() + "\n personName= " + person.getName() + "\n personSurname =" + person.getSurname() + "\n bilgilere sahip bir person güncellenememiştir.").build();
        }
    }
    //PUT   //POST  olarak http://localhost:8090/msg/personService/personCrud
    // Postmanda body kısmında
    //Json ise ornekteki gibi doldurulacaktır
    // {"id":321,
    // "name":"Muhammed",
    // "surname":"Demir"}

    @DELETE
    @Path("/personCrud")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePersonById(@QueryParam("PersonId") int personId) {
        try {
            personService.deletePerson(personId);
            return Response.status(200).entity(personId + " id li person silinmiştir").build();
        } catch (Exception e) {
            return Response.status(501).entity(personId + " id li person silinememiştir").build();
        }
    }
    //DELETE  olarak http://localhost:8090/msg/personService/personCrud?personId=4

}
