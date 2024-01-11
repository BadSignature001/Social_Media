package com.social.media.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

import com.social.media.models.Post;
import com.social.media.models.User;
import com.social.media.repository.PostRepository;
import com.social.media.repository.UserRepository;

@Service
public class PostServiceImplementaion implements PostService{
	
	@Autowired
	PostRepository postRepository ;
	
	@Autowired
	UserService userService ;
	
	@Autowired
	UserRepository userRepository ;

	@Override
	public Post createNewPost(Post post, Integer userId) throws Exception {
		
		User user = userService.findUserById(userId) ;
		Post newPost = new Post() ;
		newPost.setImage(post.getImage());
		newPost.setVideo(post.getVideo());
		newPost.setCreatedAt(LocalDateTime.now());
		newPost.setCaption(post.getCaption());
		newPost.setUser(user);
		
		Post updatedUserPost = postRepository.save(newPost) ;
		
		return updatedUserPost;
	}

	@Override
	public String deletePost(Integer postId, Integer userId) throws Exception {
		
		Post post = findPostById(postId) ;
		User user = userService.findUserById(userId) ;
		
		if(user.getId()!=post.getUser().getId())
		{
			throw new Exception("This User has not permission to delete another perosn image") ;
		}
		postRepository.delete(post);
		
		return ("Post Deleted Successfully by userId "+userId);
	}

	@Override
	public Post savedPost(Integer postId, Integer userId)throws Exception {
		
		Post post = findPostById(postId) ;
		User user = userService.findUserById(userId) ;
		
		if(user.getSavedposts().contains(user))
		{
			user.getSavedposts().remove(user) ;
		}
		
		else {
			user.getSavedposts().add(post) ;

		}
		
		userRepository.save(user) ;
		
		
		
		return post;
	}
	

	@Override
	public List<Post> findPostByUserId(Integer userId) {		
		return postRepository.findPostByUserId(userId);	
	}

	@Override
	public List<Post> findAllPost() {
	  return postRepository.findAll() ;
	}

	@Override
	public Post findPostById(Integer postId) throws Exception{
		
		Optional<Post> opt = postRepository.findById(postId) ;
		if(opt.isEmpty())
		{
			throw new Exception("Post not found by postId "+postId) ;
		}
		return opt.get();
	}

	@Override
	public Post likePost(Integer postId , Integer userId)throws Exception {
		
		Post post = findPostById(postId) ;
		User user = userService.findUserById(userId) ;
		
		if(post.getLiked().contains(user))
		{
			post.getLiked().remove(user) ;
		}
		else {
			post.getLiked().add(user) ;

		}
		
		
		return postRepository.save(post);
	}

}
