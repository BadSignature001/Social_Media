package com.social.media.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.social.media.models.Reels;
import com.social.media.models.User;
import com.social.media.service.ReelService;
import com.social.media.service.UserService;

@RestController
public class ReelsController {
	@Autowired
	UserService userService ;
	@Autowired
	ReelService reelService ;
	
	@PostMapping("/reels/create")
	public Reels createReels(@RequestBody Reels reels , @RequestHeader("Authorization")String jwt ) throws Exception
	{
		User user = userService.userProfile(jwt) ;
		Reels reel = reelService.createReels(reels , user.getId()) ;
		
		return reel ;
	}
	
	@GetMapping("/reels/userreels/{userId}")
	public List<Reels> getUserReels(@PathVariable Integer userId)
	{
		List<Reels> userreels = reelService.getAllReels() ;
		
		return userreels ;
	}
}
