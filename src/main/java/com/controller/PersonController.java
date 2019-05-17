package com.controller;

import com.model.Person;
import com.service.PersonService;
import com.service.PersonServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/service")
public class PersonController {
    PersonService personService = new PersonServiceImpl();

    @POST
    @Path("/savePerson")
    @Produces(MediaType.APPLICATION_JSON)//gelen jsondata modelle maplenecek altta tek tek ıd yollanmayacak
    public Response savePerson(@BeanParam Person person) {
        personService.createPerson(person);
        return Response.status(200).entity("id= " + person.getId() + "\n name= " + person.getName() + "\n surname =" + person.getSurname() + "\n bilgilere sahip bir person eklenmiştir.").build();
    }

    @GET
    @Path("/getPersonById")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getPersonById(@QueryParam("id") int personId) {
        Person personList = personService.findPerson(personId);
        System.out.println();
        return Response.status(200).entity(personList).build();
    }

    @PUT
    @Path("/updatePerson")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePerson(@BeanParam Person person) {
        personService.updatePerson(person);
        return Response.status(200).entity("id= " + person.getId() + "\n name= " + person.getName() + "\n surname =" + person.getSurname() + "\n bilgilere sahip bir person güncellenmistir.").build();
    }

    @DELETE
    @Path("/deletePerson")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletePersonById(@QueryParam("id") int personId) {
        try {
            personService.deletePerson(personId);
            return Response.status(200).entity(personId + " id li person silinmiştir").build();
        } catch (Exception e) {
            return Response.status(200).entity(personId + " id li person silinememiştir").build();
        }
    }

}
