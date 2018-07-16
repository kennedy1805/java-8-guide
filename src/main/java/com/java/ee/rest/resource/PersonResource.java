package com.java.ee.rest.resource;


import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.java.constants.Constants;
import com.java.ee.rest.object.model.Person;
import com.java.ee.rest.service.PersonService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;

@Path("/persons")
@SwaggerDefinition(info = @Info(description = "Api to work with Person",
        version = "V1.0.0", title = "Person REST API", termsOfService = "private"),
        schemes = {SwaggerDefinition.Scheme.HTTP, SwaggerDefinition.Scheme.HTTPS})
@Api(value = "/persons", tags = "persons")
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {

    @Inject
    private PersonService personService;
	
    
    @GET
    @ApiOperation(value = "Find persons", notes = "Find all persons."
    		+ "Or find person by name", responseContainer = "List", response = Person.class)
    @ApiResponses(value = {
        @ApiResponse(code = HttpServletResponse.SC_OK, message = Constants.MSG_OK, response = Person.class)})
    public Response findPersons(
            @ApiParam(value = "Name of person that needs to be fetched", required = false) @QueryParam("name") String name) {
        return Response.ok().entity(personService.findPersons(name)).build();
    }
    
    @GET
    @Path("/{id}")
    @ApiOperation(value = "Find person by Id", notes = "Return person details", response = Person.class)
    @ApiResponses(value = {
        @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = Constants.ENTITY_NOT_FOUND),
        @ApiResponse(code = HttpServletResponse.SC_OK, message = Constants.MSG_OK, response = Person.class)})
    public Response find(
            @ApiParam(value = "ID of person that needs to be fetched", required = true) @PathParam("id") Long id) {
        return Response.ok().entity(personService.findById(id)).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Add person", notes = "return new person after process", response = Person.class)
    @ApiResponses(value = {
        @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = Constants.ENTITY_NOT_FOUND),
        @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = Constants.MSG_VALIDATION)})
    public Response add(
            @ApiParam(value = "Person object that needs to be added", required = true) Person person) {
        return Response.ok().entity(personService.add(person)).build();
    }
    
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Update an existing person", notes = "Return person details", response = Person.class)
    @ApiResponses(value = {
        @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = Constants.ENTITY_NOT_FOUND),
        @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = Constants.MSG_VALIDATION)})
    public Response update(
            @ApiParam(value = "Person Id of person that needs to be updated", required = true) @PathParam("id") Long personId,
            @ApiParam(value = "Person object that needs to be update to the list", required = true) Person person) {
        return Response.ok().entity(personService.update(personId, person)).build();
    }
    
    @DELETE
    @Path("/{id}")
    @ApiOperation(value = "Delete person by Id", notes = "Return the result of delete", response = Response.class)
    @ApiResponses(value = {
        @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = Constants.ENTITY_NOT_FOUND),
        @ApiResponse(code = HttpServletResponse.SC_OK, message = Constants.MSG_OK)})
    public Response delete(
            @ApiParam(value = "Id of person that needs to be deleted", required = true) @PathParam("id") Long id) {
        personService.remove(id);
        return Response.ok().build();
    }
}
