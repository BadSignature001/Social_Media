package com.social.media.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Comment {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id ;
	private String comment;
	@ManyToOne
	private User user ;
	private LocalDateTime createdAt;	
	@ManyToMany
	private List<User> likes = new ArrayList<>() ;
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public List<User> getLikes() {
		return likes;
	}
	public void setLikes(List<User> likes) {
		this.likes = likes;
	}
	public Comment(Integer id, String comment, User user, LocalDateTime createdAt, List<User> likes) {
		super();
		Id = id;
		this.comment = comment;
		this.user = user;
		this.createdAt = createdAt;
		this.likes = likes;
	}
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Comment [Id=" + Id + ", comment=" + comment + ", user=" + user + ", createdAt=" + createdAt + ", likes="
				+ likes + "]";
	}
	
	
	
	
	

}
