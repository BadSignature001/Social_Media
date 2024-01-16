package com.social.media.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.media.models.Comment;
import com.social.media.models.Post;
import com.social.media.models.User;
import com.social.media.repository.CommentRepository;
import com.social.media.repository.PostRepository;

@Service
public class CommentServiceImplementation implements CommentService{

	@Autowired
	private PostService postService ;
	@Autowired
	private UserService userService ;
	@Autowired
	private CommentRepository commentRepository ;
	@Autowired
	private PostRepository postRepository ;
	
	@Override
	public Comment createComment(Comment comment2, Integer postId, Integer userId) throws Exception {
		Post post = postService.findPostById(postId) ;
		User user = userService.findUserById(userId) ;
		
		comment2.setComment(comment2.getComment());
		comment2.setUser(user);
		comment2.setCreatedAt(LocalDateTime.now());
		
		Comment savedComment = commentRepository.save(comment2) ;
		
		
		post.getComments().add(savedComment);
		Post updatedPost = postRepository.save(post) ;
		
		
		
		
		return savedComment;
	}

	@Override
	public String deleteComment(Integer commentId, Integer userId) throws Exception {
		Comment comment  =  findCommentById(commentId) ;
		if(comment == null)
		{
			throw new Exception ("Here is no comment avaliable for this comment ID") ;
		}
		commentRepository.delete(comment) ;
		return "Comment Deleted Successfully of commentID"+commentId ;
	}

	@Override
	public Comment likeComment(Integer commentId, Integer userId) throws Exception{
		User user = userService.findUserById(userId) ;
		Comment postComment = findCommentById(commentId) ;
		
		if(postComment.getLikes().contains(user))
		{
			postComment.getLikes().remove(user) ;
			
			Comment updatedComment = commentRepository.save(postComment) ;
		}
		else {
			postComment.getLikes().add(user) ;
			
		}
		
		Comment updatedComment = commentRepository.save(postComment) ;
		
		return updatedComment;
	}

	@Override
	public Comment findAllComment(Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comment findCommentById(Integer commentId) throws Exception {
		
		Optional<Comment> comment = commentRepository.findById(commentId) ;
		
		if(comment.isEmpty())
		{
			throw new Exception("There is no comment avaliable for this commentId" +commentId) ;
		}
		
		return comment.get();
	}

	

}
