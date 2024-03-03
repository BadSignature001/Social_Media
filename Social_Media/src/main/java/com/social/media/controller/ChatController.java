package com.social.media.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.social.media.models.Chat;
import com.social.media.models.User;
import com.social.media.request.CreateChatRequest;
import com.social.media.service.ChatService;
import com.social.media.service.UserService;

@RestController
public class ChatController {
	
	@Autowired
	private ChatService chatService ;
	
	@Autowired
	private UserService userService ;
	
	@PostMapping("/chat/createchat")
	public Chat createChat(@RequestHeader("Authorization")String jwt , @RequestBody CreateChatRequest req) throws Exception
	{
		User reqUser=userService.userProfile(jwt) ;
		User user2 = userService.findUserById(req.getUserId()) ;
		Chat chat=chatService.createChat(reqUser, user2) ;
		return chat ;
	}
	
	@GetMapping("/chat/findchats")
	public List<Chat> findUsersChat(@RequestHeader("Authorization")String jwt) throws Exception
	{
		User user=userService.userProfile(jwt) ;
		List<Chat> chats=chatService.findUserChat(user.getId()) ;
		return chats ;
	}
	

}
