package com.social.media.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "posts")
public class Post {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id ;
	private String image ;
	private String video ;
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	private String caption ;
	@ManyToOne
	private User user ;
	private LocalDateTime createdAt ;
	
	@OneToMany
	private List<User> liked = new ArrayList<>() ;
	
	
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
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
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public List<User> getLiked() {
		return liked;
	}
	public void setLiked(List<User> liked) {
		this.liked = liked;
	}
	public Post(Integer id, String image, String video, String caption, User user, LocalDateTime createdAt,
			List<User> liked) {
		super();
		Id = id;
		this.image = image;
		this.video = video;
		this.caption = caption;
		this.user = user;
		this.createdAt = createdAt;
		this.liked = liked;
	}
	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Post [Id=" + Id + ", image=" + image + ", video=" + video + ", caption=" + caption + ", user=" + user
				+ ", createdAt=" + createdAt + ", liked=" + liked + "]";
	}
	
	
	
	
	
	
	

	
	
	

}
