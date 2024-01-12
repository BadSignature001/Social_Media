package com.social.media.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.social.media.models.Post;
import com.social.media.service.PostService;
import com.social.media.service.UserService;

@RestController
public class PostController {
	
	@Autowired
	PostService postService ;
	
	@GetMapping("/posts")
	public ResponseEntity<List<Post>> getAllPosts()
	{
		List<Post> posts = postService.findAllPost() ;
		
		return new ResponseEntity <List<Post>>(posts,HttpStatus.ACCEPTED) ;
	}
	
	@GetMapping("/posts/postid/{postId}")
	public ResponseEntity<Post> getPostByPostId(@PathVariable("postId") Integer postId) throws Exception
	{
		Post post = postService.findPostById(postId) ;
		
		return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED) ;
	}
	
	@GetMapping("/posts/user/{userId}")
	public ResponseEntity<List<Post>> getPostByUserId(@PathVariable("userId") Integer userId)throws Exception
	{
		List<Post> post = postService.findPostByUserId(userId) ;
		return new ResponseEntity <List<Post>>(post,HttpStatus.OK) ;
	}
	
	 @DeleteMapping("/posts/delete/{postId}/{userId}")
	    public ResponseEntity<String> deletePost(@PathVariable Integer postId, @PathVariable Integer userId) {
	        try {
	            String result = postService.deletePost(postId, userId);
	            return new ResponseEntity<>(result, HttpStatus.OK);
	        } catch (Exception e) {
	            // Handle exceptions appropriately, for simplicity, just returning a 400 Bad Request
	            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	        }
	    }
	 @PostMapping("/posts/create/{userId}")
	 public ResponseEntity<Post> createPost(@RequestBody Post post, @PathVariable Integer userId) {
	     try {
	         Post newPost = postService.createNewPost(post, userId);
	         return new ResponseEntity<>(newPost, HttpStatus.CREATED);
	     } catch (Exception e) {
	         // Handle exceptions appropriately, for simplicity, just returning a 400 Bad Request
	         return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	     }
	 }
	 
	 @PutMapping("/posts/save/{userId}/{postId}")
	 public ResponseEntity<Post> savedPost(@PathVariable Integer postId , @PathVariable Integer userId) throws Exception
	 {
		 Post savedPost = postService.savedPost(postId, userId) ;
		 return new ResponseEntity<>(savedPost, HttpStatus.OK);
	 }
	 
	 @PutMapping("/posts/like/{userId}/{postId}")
	 public ResponseEntity<Post> likedPost(@PathVariable Integer postId , @PathVariable Integer userId) throws Exception
	 {
		 try {
	         Post likePost = postService.likePost(postId, userId);
	         return new ResponseEntity<>(likePost, HttpStatus.CREATED);
	     } catch (Exception e) {
	         // Handle exceptions appropriately, for simplicity, just returning a 400 Bad Request
	         return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	     }

	 }
}
