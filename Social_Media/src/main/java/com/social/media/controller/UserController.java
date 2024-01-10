package com.social.media.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.social.media.models.User;
import com.social.media.repository.UserRepository;
import com.social.media.service.UserService;

@RestController 
public class UserController {
	
	@Autowired
	UserRepository userRepository ;
	
	@Autowired
	UserService userService ;
	
	@PostMapping("/regUser")
	public User createUser(@RequestBody User user) {
		User savedUser = userService.registerUser(user);
		return savedUser;
	}
	
	@GetMapping("/users/{userId}")
	public User getUserById(@PathVariable("userId") Integer userId) throws Exception {
		User getUser = userService.findUserById(userId);
		return getUser;
	}
	
	@GetMapping("/users/by-email/{userEmail}")
	public User getUserByEmail(@PathVariable("userEmail") String userEmail) throws Exception {
		User getUser = userService.findUserByEmail(userEmail);
		return getUser;
	}
	
	@PutMapping("/users/{userId}")
	public User updateUser(@RequestBody User user, @PathVariable Integer userId) throws Exception {
		User updatedUser = userService.updateUser(user, userId);
		return updatedUser;
	}
	
	@PutMapping("/users/follow/{userId1}/{userId2}")
	public User followUserHandler(@PathVariable Integer userId1, @PathVariable Integer userId2) throws Exception {
		User updatedUser = userService.followUser(userId1, userId2);
		return updatedUser;
	}
	
	@GetMapping("/users/search")
	public List<User> searchUser(@RequestParam("query") String query) {
		List<User> users = userService.searchUser(query);
		return users;
	}
}

