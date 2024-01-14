package com.social.media.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
	
	@PutMapping("/users/update")
	public User updateUser(@RequestBody User user, @RequestHeader("Authorization")String jwt) throws Exception {
		User userId = userService.userProfile(jwt); 
		User updatedUser = userService.updateUser(user, userId.getId());
		return updatedUser;
	}
	
	@PutMapping("/users/follow/{userId2}")
	public User followUserHandler(@RequestHeader("Authorization")String jwt, @PathVariable Integer userId2) throws Exception {
		
		User userId1 = userService.userProfile(jwt) ;
		User updatedUser = userService.followUser(userId1.getId(), userId2);
		return updatedUser;
	}
	
	@PutMapping("/users/unfollow/{userId2}")
	public User unfollowUserHandler(@RequestHeader("Authorization")String jwt , @PathVariable Integer userId2)throws Exception
	{
		User userId1 = userService.userProfile(jwt) ;
		User updatedUser = userService.unfollowUser(userId1.getId(), userId2) ;
		
		return updatedUser ;
	}
	
	@GetMapping("/users/search")
	public List<User> searchUser(@RequestParam("query") String query) {
		List<User> users = userService.searchUser(query);
		return users;
	}
	
	@GetMapping("/users/profile")
	public User getUserProfile(@RequestHeader("Authorization")String jwt)throws Exception
	{
		User user = userService.userProfile(jwt); 
		user.setPassword(null) ;
		return user ;
	}
}

