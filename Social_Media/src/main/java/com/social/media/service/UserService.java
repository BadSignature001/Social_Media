package com.social.media.service;

import java.util.List;

import com.social.media.models.User;

public interface UserService {
	
	public User registerUser(User user) ;
	public User findUserById(Integer userId) throws Exception;
	public User findUserByEmail(String email) throws Exception ;
	public User followUser(Integer userId1, Integer userId2)throws Exception ;
	public User unfollowUser(Integer userId1 , Integer userId2)throws Exception ;
	public User updateUser(User user , Integer userId)throws Exception ;
	public List<User> searchUser(String query) ;
	public User userProfile(String jwt)throws Exception ;
}
