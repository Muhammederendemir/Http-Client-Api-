package com.service;

import com.dao.PersonDAO;
import com.dao.PersonDAOImpl;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/service")
public class PersonService {
    PersonDAO personDAO = new PersonDAOImpl();

    @POST
    @Path("/savePerson")
    @Produces(MediaType.APPLICATION_JSON)
    public Response savePerson(@QueryParam("id") int id, @QueryParam("name") String name, @QueryParam("surname") String surname) {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("id", id);
        jsonObject.put("name", name);
        jsonObject.put("surname", surname);

        String output = personDAO.savePerson(jsonObject);
        return Response.status(200).entity(output).build();
    }

    @GET
    @Path("/getPersonById")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getPersonById(@QueryParam("id") int personId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", personId);
        String output = personDAO.getPersonById(jsonObject);
        return Response.status(200).entity(output).build();
    }

    @PUT
    @Path("/updatePerson")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePerson(@QueryParam("id") int id, @QueryParam("name") String name, @QueryParam("surname") String surname) {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("id", id);
        jsonObject.put("name", name);
        jsonObject.put("surname", surname);

        String output = personDAO.updatePerson(jsonObject);
        return Response.status(200).entity(output).build();
    }

    @DELETE
    @Path("/deletePerson")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletePersonById(@QueryParam("id") int personId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", personId);
        String output = personDAO.deletePersonById(jsonObject);
        return Response.status(200).entity(output).build();
    }
}
