package com.mithrandir.springcrud.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.mithrandir.springcrud.entity.User;

public interface UserService {
	
	public void createUser(User user);
	public UserDetails getUserByUsername(String username);

}
