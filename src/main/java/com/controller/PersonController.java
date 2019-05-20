package com.controller;

import com.model.Person;
import com.service.PersonService;
import com.service.PersonServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/service")
public class PersonController {
    PersonService personService = new PersonServiceImpl();

    @POST
    @Path("/personCrud")
    @Produces(MediaType.APPLICATION_JSON)
    public Response savePerson(@BeanParam Person person) {
        try {
            personService.createPerson(person);
            return Response.status(201).entity("id= " + person.getId() + "\n name= " + person.getName() + "\n surname =" + person.getSurname() + "\n bilgilere sahip bir person eklenmiştir.").build();
        } catch (Exception e) {
            return Response.status(501).entity("id= " + person.getId() + "\n name= " + person.getName() + "\n surname =" + person.getSurname() + "\n bilgilere sahip bir person eklenememiştir.").build();
        }
    }
    //POST  olarak http://localhost:8090/msg/service/personCrud?personId=4&personName=isim&personSurname=soyisim

    @GET
    @Path("/personCrud")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getPersonById(@QueryParam("personId") int personId) {
        Person personList = null;
        try {
            personList = personService.findPerson(personId);
            return Response.status(200).entity(personList).build();
        } catch (Exception e) {
            return Response.status(501).entity("istenilen işlem gercekleştirilemedi").build();
        }
    }
    //GET  olarak http://localhost:8090/msg/service/personCrud?personId=4

    @PUT
    @Path("/personCrud")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePerson(@BeanParam Person person) {
        try {
            personService.updatePerson(person);
            return Response.status(200).entity("personId= " + person.getId() + "\n personName= " + person.getName() + "\n personSurname =" + person.getSurname() + "\n bilgilere sahip bir person güncellenmistir.").build();
        } catch (Exception e) {
            return Response.status(501).entity("personId= " + person.getId() + "\n personName= " + person.getName() + "\n personSurname =" + person.getSurname() + "\n bilgilere sahip bir person güncellenememiştir.").build();
        }
    }
    //PUT   //POST  olarak http://localhost:8090/msg/service/personCrud?personId=4&personName=isim&personSurname=soyisim

    @DELETE
    @Path("/personCrud")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletePersonById(@QueryParam("PersonId") int personId) {
        try {
            personService.deletePerson(personId);
            return Response.status(200).entity(personId + " id li person silinmiştir").build();
        } catch (Exception e) {
            return Response.status(501).entity(personId + " id li person silinememiştir").build();
        }
    }
    //DELETE  olarak http://localhost:8090/msg/service/personCrud?personId=4

}
