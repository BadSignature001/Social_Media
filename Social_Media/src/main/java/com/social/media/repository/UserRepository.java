package com.social.media.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.social.media.models.User;

public interface UserRepository extends JpaRepository<User,Integer> {
	public User findByEmail(String email);
	
	/*@Query("SELECT DISTINCT u FROM User u WHERE u.firstname LIKE %:query%OR u.lastname LIKE %:query% OR u.email LIKE %:query%")
	public List<User> searchUser(@Param("query") String query);*/
	
	@Query("SELECT u FROM User u WHERE u.firstname LIKE %:query% OR u.lastname LIKE %:query% OR u.email LIKE %:query%")
	public List<User> searchUser(@Param("query") String query);
}
