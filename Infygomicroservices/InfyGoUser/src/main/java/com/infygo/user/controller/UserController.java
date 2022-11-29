package com.infygo.user.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.infygo.user.DTO.UserDTO;
import com.infygo.user.exception.AlreadyExistsException;
import com.infygo.user.exception.InvalidCredentialsException;
import com.infygo.user.exception.NotExstingException;
import com.infygo.user.service.UserService;

@RestController
@RequestMapping("/infygo")
public class UserController {
	
	@Autowired
	private UserService userService;

	//Register new user 
	@PostMapping("/registeruser")
	public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserDTO userDTO)
			throws AlreadyExistsException{
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(userService.registerUser(userDTO));
	}
	
	
	//Login for user
	@PostMapping("/loginuser/{userId}/{passward}")
	public ResponseEntity<String> userLogin(@PathVariable("userId") String userId,
			@PathVariable("passward") String passward)
			throws InvalidCredentialsException, NotExstingException{
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.userLogin(userId,passward));
	}
	
	//Provides user details based on userId
	@GetMapping("/userdetails/{userId}")
	public ResponseEntity<UserDTO> userDetails(@PathVariable("userId") String userId) throws NotExstingException {
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.getUserDeailsById(userId)); 
	}
}
