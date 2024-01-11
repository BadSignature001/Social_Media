package com.social.media.service;

import java.util.List;

import com.social.media.models.Post;

public interface PostService {
	
	public Post createNewPost(Post post , Integer userId)throws Exception ;
	public String deletePost(Integer postId , Integer userId)throws Exception ;
	public Post savedPost(Integer postId , Integer userId) throws Exception;
	public List<Post> findPostByUserId(Integer userId) ;
	public List<Post> findAllPost() ;
	public Post findPostById(Integer postId)throws Exception ;
	public Post likePost(Integer postId , Integer userId)throws Exception ;
	
	

}
