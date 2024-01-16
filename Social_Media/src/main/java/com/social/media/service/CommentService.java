package com.social.media.service;

import com.social.media.models.Comment;
import com.social.media.models.User;

public interface CommentService {
	public Comment createComment(Comment comment2,Integer postId , Integer userId) throws Exception  ;
	public String deleteComment(Integer commentId , Integer userId ) throws Exception;
	public Comment likeComment(Integer commentId , Integer userId) throws Exception;
	public Comment findAllComment(Integer postId) ;
	public Comment findCommentById(Integer commentId) throws Exception ;
	

}
