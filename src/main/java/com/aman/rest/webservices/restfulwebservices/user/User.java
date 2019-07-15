package com.aman.rest.webservices.restfulwebservices.user;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Size(min=2,message="Name should have atleast 2 Charecter")
	private String name;
	
	@Past
	private Date dob;
	
	@JsonIgnore
	private String password;
	
	@OneToMany(mappedBy="user")
	private List<Post> posts;
		
	public User() {
		super();
	}

	/**
	 * @param id
	 * @param name
	 * @param dob
	 */
	public User(Integer id, String name, Date dob,String pwd) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
		this.password=pwd;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}		
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", dob=" + dob + "]";
	}
	
	
}
