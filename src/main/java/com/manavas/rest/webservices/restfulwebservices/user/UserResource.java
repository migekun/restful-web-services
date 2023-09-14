package com.manavas.rest.webservices.restfulwebservices.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
	public User retrieveAllUsers(@PathVariable int id){
		return userDaoService.findOne(id);
	}
	
	@PostMapping(path = "/users")
	public void createUsers(@RequestBody User user) {
		userDaoService.save(user);
	}
	
}
