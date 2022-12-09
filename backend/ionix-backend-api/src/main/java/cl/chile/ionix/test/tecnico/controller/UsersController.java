package cl.chile.ionix.test.tecnico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cl.chile.ionix.test.tecnico.dto.UsersDTO;
import cl.chile.ionix.test.tecnico.response.ResponseDefault;
import cl.chile.ionix.test.tecnico.response.ResponseUser;
import cl.chile.ionix.test.tecnico.response.ResponseUsers;
import cl.chile.ionix.test.tecnico.service.UsersService;
import lombok.extern.log4j.Log4j2;

/**
 * The Class UsersController.
 */
@RestController

/** The Constant log. */
@Log4j2
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE })
@RequestMapping(value="/api")
public class UsersController {
	
	/** The service. */
	@Autowired
	private UsersService service;
	
	/**
	 * List users.
	 *
	 * @return the response entity
	 */
	@GetMapping(value = "/users", produces = "application/json")
	public ResponseEntity<ResponseUsers> listUsers() {
		log.info("-- Init endpoint list users--");
		ResponseUsers response = service.listUsers();
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	/**
	 * Creates the user.
	 *
	 * @param usr the usr
	 * @return the response entity
	 */
	@PostMapping(value = "/user/new", produces = "application/json")
	public ResponseEntity<ResponseDefault> createUser(@RequestBody UsersDTO usr) {
		log.info("-- Init endpoint insert user--");
		ResponseDefault response = service.createUser(usr);;
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	/**
	 * Gets the user.
	 *
	 * @param email the email
	 * @return the user
	 */
	@GetMapping(value = "/user/search/{email}", produces = "application/json")
	public ResponseEntity<ResponseUser> getUser(@PathVariable("email") String email) {
		log.info("-- Init endpoint get user with email--");
		ResponseUser response = service.getUser(email);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	/**
	 * Delete user.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@DeleteMapping(value = "/user/delete/{id}", produces = "application/json")
	public ResponseEntity<ResponseDefault> deleteUser(@PathVariable("id") Long id) {
		return new ResponseEntity<>(service.deleteUser(id), HttpStatus.OK);
	}
	

}
