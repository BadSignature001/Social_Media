package com.social.media.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Reels {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id ;
	private String video ;
	private String caption  ;
	@ManyToOne
	private User user ;
	@ManyToMany
	private List<User> like = new ArrayList<>();
	@ManyToMany
	private List<Comment> comment = new ArrayList<>()  ;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<User> getLike() {
		return like;
	}
	public void setLike(List<User> like) {
		this.like = like;
	}
	public List<Comment> getComment() {
		return comment;
	}
	public void setComment(List<Comment> comment) {
		this.comment = comment;
	}
	public Reels(Integer id, String video, String caption, User user, List<User> like, List<Comment> comment) {
		super();
		this.id = id;
		this.video = video;
		this.caption = caption;
		this.user = user;
		this.like = like;
		this.comment = comment;
	}
	public Reels() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Reels [id=" + id + ", video=" + video + ", caption=" + caption + ", user=" + user + ", like=" + like
				+ ", comment=" + comment + "]";
	}
	
	
	
	
	
	
	

}
