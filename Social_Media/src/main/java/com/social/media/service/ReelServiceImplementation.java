package com.social.media.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.media.models.Post;
import com.social.media.models.Reels;
import com.social.media.models.User;
import com.social.media.repository.ReelsRepository;

@Service
public class ReelServiceImplementation implements ReelService{

	@Autowired
	UserService userService ;
	@Autowired
	ReelsRepository reelsRepository ;
	
	@Override
	public Reels createReels(Reels video, Integer userId) throws Exception {
		
		User user = userService.findUserById(userId) ;
		Reels reel = new Reels() ;
		reel.setVideo(video.getVideo());
		reel.setCaption(video.getCaption());
		reel.setUser(user);
		
		Reels newReel = reelsRepository.save(reel) ;
		
		return newReel;
	}

	@Override
	public String deleteReel(Integer reelId, Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reels> getAllReels() {
		
		
		
		return reelsRepository.findAll();
	}

	@Override
	public List<Reels> getUserReels(Integer userId) {
		
		
		
		return reelsRepository.findReelsByUserId(userId);
	}
	
	
	@Override
	public Reels findReelById(Integer reelId) throws Exception{
		
		Optional<Reels> opt = reelsRepository.findById(reelId) ;
		if(opt.isEmpty())
		{
			throw new Exception("Reel not found by postId "+reelId) ;
		}
		return opt.get();
	}


	@Override
	public Reels likeReels(Integer reelId, Integer userId) throws Exception {
		
		Reels reel = findReelById(reelId) ;
		User user = userService.findUserById(userId) ;
		
		if(reel.getLike().contains(user))
		{
			reel.getLike().remove(user) ;
		}
		else {
			reel.getLike().add(user) ;

		}
		
		
		return reelsRepository.save(reel);
		
		
	}
	
	

}
