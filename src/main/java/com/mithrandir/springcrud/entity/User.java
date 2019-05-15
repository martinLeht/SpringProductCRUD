package com.mithrandir.springcrud.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="users")
public class User {
	
	/*
	@Column(name = "firstname")
	private String firstName;
	
	@Column(name = "lastname")
	private String lastName;
	
	@Column(name = "email")
	private String email;
	*/
	
	@Id
	@Column(name = "username")
	private String username;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "enabled", nullable = false)
	private int enabled;
	
	@Column(name = "creation_datetime")
	@CreationTimestamp
	private LocalDateTime createDateTime;

	@Column(name = "last_updated")
	@UpdateTimestamp
	private LocalDateTime updateDateTime;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Authorities> authorities = new HashSet<>();

	
	public User() {
		
	}


	/*
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	*/
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		if (enabled == 1) {
			return true;
		} else {
			return false;
		}
		
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	
	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(LocalDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}


	public LocalDateTime getUpdateDateTime() {
		return updateDateTime;
	}


	public void setUpdateDateTime(LocalDateTime updateDateTime) {
		this.updateDateTime = updateDateTime;
	}


	public Set<Authorities> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authorities> authorities) {
		this.authorities = authorities;
	}
	
	
	// add convenience methods for bi-directional relationships
	public void addAuthority(Authorities authority) {
		
		if (authorities == null) {
			authorities = new HashSet<>();
		}
		
		authority.setUser(this);
		authorities.add(authority);
		
	}
	
}
