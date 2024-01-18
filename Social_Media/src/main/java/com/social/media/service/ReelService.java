package com.social.media.service;

import java.util.List;

import com.social.media.models.Post;
import com.social.media.models.Reels;
import com.social.media.models.User;

public interface ReelService {
	
	public Reels createReels(Reels video , Integer userId) throws Exception ;
	public String deleteReel(Integer reelId , Integer userId) ;
	public List<Reels> getAllReels() ;
	public List<Reels> getUserReels(Integer userId) ;
	public Reels likeReels(Integer reelId ,Integer userId) throws Exception ;
	public Reels findReelById(Integer reelId) throws Exception;

}
