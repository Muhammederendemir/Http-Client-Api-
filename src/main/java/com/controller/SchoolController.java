package com.controller;

import com.model.School;
import com.service.SchoolService;
import com.service.SchoolServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/schoolService")
public class SchoolController {
    SchoolService schoolService = new SchoolServiceImpl();

    @POST
    @Path("/schoolCrud")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveSchool(@BeanParam School school) {
        try {
            schoolService.createSchool(school);
            return Response.status(201).entity(" schoolNo= " + school.getSchoolNo() + "\n schoolname= " + school.getSchoolName() + "\n countryNo= " + school.getCountryNo() + "\n bilgilere sahip bir school eklenmiştir.").build();
        } catch (Exception e) {
            return Response.status(501).entity(" schoolNo= " + school.getSchoolNo() + "\n schoolname= " + school.getSchoolName() + "\n countryNo= " + school.getCountryNo() + "\n bilgilere sahip bir school eklenememiştir.").build();
        }
    }
    //POST  olarak http://localhost:8090/msg/service/schoolCrud?schoolNo=4&schoolName=isim&countryNo=1

    @GET
    @Path("/schoolCrud")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getSchoolByNo(@QueryParam("schoolNo") int schoolNo) {
        School school = null;
        try {
            school = schoolService.findSchool(schoolNo);
            return Response.status(200).entity(school).build();
        } catch (Exception e) {
            return Response.status(501).entity("istenilen işlem gercekleştirilemedi").build();
        }
    }
    //GET olarak http://localhost:8090/msg/service/schoolCrud?schoolNo=4

    @PUT
    @Path("/schoolCrud")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateSchool(@BeanParam School school) {
        try {
            schoolService.updateSchool(school);
            return Response.status(200).entity(" schoolNo= " + school.getSchoolNo() + "\n schoolname= " + school.getSchoolName() + "\n countryNo= " + school.getCountryNo() + "\n bilgilere sahip bir person güncellenmistir.").build();
        } catch (Exception e) {
            return Response.status(501).entity(" schoolNo= " + school.getSchoolNo() + "\n schoolname= " + school.getSchoolName() + "\n countryNo= " + school.getCountryNo() + "\n bilgilere sahip bir person güncellenememiştir.").build();
        }

    }

    //PUT  olarak http://localhost:8090/msg/service/schoolCrud?schoolNo=4&schoolName=isim&countryNo=1
    @DELETE
    @Path("/schoolCrud")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteSchoolByNo(@QueryParam("schoolNo") int schoolNo) {
        try {
            schoolService.deleteSchool(schoolNo);
            return Response.status(200).entity(schoolNo + " id li school silinmiştir").build();
        } catch (Exception e) {
            return Response.status(501).entity(schoolNo + " id li school silinememiştir").build();
        }
    }
    //DELETE  olarak http://localhost:8090/msg/service/schoolCrud?schoolNo=4
}
