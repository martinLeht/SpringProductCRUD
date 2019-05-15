package com.mithrandir.springcrud.dao;

import com.mithrandir.springcrud.entity.User;

public interface UserDAO {
	
	public void createUser(User user);
	public User findUserByUsername(String username);
}
