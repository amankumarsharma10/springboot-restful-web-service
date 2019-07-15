package com.aman.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResouce {

	@Autowired
	UserDaoService service;

	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}

/*	
 	//get user
 	@GetMapping("/users/{id}")
	public User retrieveUser(@PathVariable Integer id) {
		User user = service.findOne(id);
		if (user == null) {
			throw new UserNotFoundException("Not found user id-" + id);
		}
		return user;
	}
*/
	//get user with HATEOAS
	@GetMapping("/users/{id}")
	public Resource<User> retrieveUser(@PathVariable Integer id) {
		User user = service.findOne(id);
		if (user == null) {
			throw new UserNotFoundException("Not found user id-" + id);
		}
		
		//HATEOAS
		//all-users, SERVER_PATH + "/users"
		Resource<User> resource = new Resource<User>(user);
		ControllerLinkBuilder linkTo = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
		resource.add(linkTo.withRel("all-users"));
		return resource;
	}
	
	@PostMapping("users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user);
		// users/{id}
		// Returning saved user url
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable Integer id) {
		User user = service.deleteById(id);
		if (user == null) {
			throw new UserNotFoundException("user id-" + id);
		}
	}
}
