package com.manavas.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {

	private UserDaoService userDaoService;

	@Autowired
	public void UserDaoService(UserDaoService userDaoService) {
		this.userDaoService = userDaoService;
	}
	
	@GetMapping(path = "/users")
	public List<User> retrieveAllUsers(){
		return userDaoService.findAll();
	}
	
	@GetMapping(path = "/users/{id}")
	public EntityModel<User> retrieveAllUsers(@PathVariable int id){

		User found = userDaoService.findOne(id);
		if (found == null) {
            throw new UserNotFoundException("id: " + id);
        }
		EntityModel<User> userEntityModel =  EntityModel.of(found);
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		userEntityModel.add(link.withRel("all-users"));
		return userEntityModel;
	}

	@DeleteMapping (path = "/users/{id}")
	public void deleteUser(@PathVariable int id){
		userDaoService.deleteUserById(id);
	}
	
	@PostMapping(path = "/users")
	public ResponseEntity<User> createUsers(@Valid @RequestBody User user) {
		User savedUser = userDaoService.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
}
