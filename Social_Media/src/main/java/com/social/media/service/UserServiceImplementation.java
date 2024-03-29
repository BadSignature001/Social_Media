package com.social.media.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.media.config.JwtProvider;
import com.social.media.models.User;
import com.social.media.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService{
	
	@Autowired
	UserRepository userRepository ;
	
	@Override
	public User registerUser(User user) {
		User newuser = new User() ;
		newuser.setFirstname(user.getFirstname());
		newuser.setLastname(user.getLastname());
		newuser.setEmail(user.getEmail());
		newuser.setPassword(user.getPassword());
		
		User savedUser = userRepository.save(newuser) ;
		return savedUser;
	}

	@Override
	public User findUserById(Integer userId)throws Exception{
		
		Optional<User> user = userRepository.findById(userId) ;
		
		if(user.isPresent())
		{
			return user.get() ;
		}
		throw new Exception("user not exist with userid" +userId) ;
		
		
	}

	@Override
	public User findUserByEmail(String email){				
		User user1 = userRepository.findByEmail(email) ;		
		return user1 ;
	}
	
	

	@Override
	public User followUser(Integer userId1, Integer userId2)throws Exception {
		User user1 = findUserById(userId1) ;
		User user2 = findUserById(userId2) ;
		
		user2.getFollowers().add(user1.getId()) ;
		user1.getFollowings().add(user2.getId()) ;
		
		userRepository.save(user1) ;
		userRepository.save(user2) ;
		return user1;
	}
	
	

	@Override
	public User updateUser(User user, Integer userId) throws Exception{
		
		Optional<User> user1 = userRepository.findById(userId) ;
		
		if(user1.isEmpty())
		{
			throw new Exception("User Not Found with UserId" +userId) ;
		}
		
		User oldUser = user1.get() ;
		if(user.getFirstname()!=null)
		{
			oldUser.setFirstname(user.getFirstname());
		}
		if(user.getLastname()!=null)
		{
			oldUser.setLastname(user.getLastname());
		}
		if(user.getEmail()!=null)
		{
			oldUser.setEmail(user.getEmail());
		}
		if(user.getGender()!=null)
		{
			oldUser.setGender(user.getGender());
		}
		if(user.getPassword()!=null)
		{
			oldUser.setPassword(user.getPassword());
		}
		
		User updatedUser = userRepository.save(oldUser) ;
		
		return updatedUser;
	}

	@Override
	public List<User> searchUser(String query) {
		return userRepository.searchUser(query) ;
		
	}

	@Override
	public User unfollowUser(Integer userId1, Integer userId2) throws Exception {
		
		User user1 = findUserById(userId1) ;
		User user2 = findUserById(userId2) ;
		
		user1.getFollowings().remove(user2.getId()) ;
		user2.getFollowers().remove(user1.getId()) ;
		
		userRepository.save(user1) ;
		userRepository.save(user2) ;
		
		return user1;
	}

	@Override
	public User userProfile(String jwt) throws Exception {
		
		String email = JwtProvider.getEmailFromJwtToken(jwt) ;
		User user = userRepository.findByEmail(email) ;
		
		return user;
	}

}
