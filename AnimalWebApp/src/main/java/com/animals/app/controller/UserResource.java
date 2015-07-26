package com.animals.app.controller;

import com.animals.app.domain.User;
import com.animals.app.repository.UserRepositoryImpl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("users") //http:localhost:8080/AnimalsWebApp/webapi/users
public class UserResource {
	
	UserRepositoryImpl userRep = new UserRepositoryImpl();
	
	@GET //http:localhost:8080/AnimalsWebApp/webapi/users
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})	
	public List<User> getAllUsers() {
		
		return userRep.getAll();
		
	}
	
	@GET //http:localhost:8080/AnimalsWebApp/webapi/users/id
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("{userId}") //http:localhost:8080/AnimalsWebApp/webapi/users/id
	public User getUserById(@PathParam ("userId") String id) {
		
		int idi = (int) Integer.parseInt(id);
		
		return userRep.getById(idi);
		
	}

}