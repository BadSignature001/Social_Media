package com.social.media.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.social.media.models.Reels;

public interface ReelsRepository extends JpaRepository<Reels,Integer> {


	
	@Query("select r from Reels r where r.user.id=:userId")
	List<Reels> findReelsByUserId(Integer userId) ;
}
