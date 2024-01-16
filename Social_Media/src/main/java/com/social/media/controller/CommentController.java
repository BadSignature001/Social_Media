package com.social.media.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.social.media.models.Comment;
import com.social.media.models.User;
import com.social.media.repository.CommentRepository;
import com.social.media.service.CommentService;
import com.social.media.service.UserService;

@RestController
public class CommentController {
	
	@Autowired
	private CommentService commentService ;
	@Autowired
	private CommentRepository commentRepository ;
	
	@Autowired
	private UserService userService ;
	
	
	
	@PostMapping("/comment/{postId}")
	public Comment createComment(@RequestBody Comment comment ,@RequestHeader("Authorization")String jwt ,@PathVariable Integer postId )throws Exception
	{
		User user = userService.userProfile(jwt) ;
		Comment createComment =  commentService.createComment(comment, postId, user.getId()) ;
		return createComment ;
	}
	
	@PutMapping("/comment/like/{commentId}")
	public Comment like(@RequestHeader("Authorization")String jwt ,@PathVariable Integer commentId )throws Exception
	{
		User user = userService.userProfile(jwt) ;
		Comment addLike =  commentService.likeComment(commentId, user.getId()) ;
		return addLike ;
	}
	/*
	@DeleteMapping("/comment/delete/{commentId}")
	public String deleteComment(@RequestHeader("Authorization")String jwt ,@PathVariable Integer commentId )throws Exception
	{
		User user = userService.userProfile(jwt) ;
		Comment comment = commentService.findCommentById(commentId) ;
		User cmtUser = commentRepository.findUserByCommentId(commentId) ;
		
		if(cmtUser.getId() != user.getId())
		{
			throw new Exception("Bhai tu kisi aur ka comment delte nahi kar sakta ") ;
		}
		
		commentService.deleteComment(commentId, user.getId()) ;
		return "Comment Deleted Successfully" ;
	} */
	
	

}
