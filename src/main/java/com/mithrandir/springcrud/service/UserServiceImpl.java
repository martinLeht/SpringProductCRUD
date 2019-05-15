package com.mithrandir.springcrud.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mithrandir.springcrud.dao.UserDAOImpl;
import com.mithrandir.springcrud.entity.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAOImpl userDao;
	
	@Override
	@Transactional
	public void createUser(User user) {
		userDao.createUser(user);
	}
	
	@Override
	@Transactional
	public UserDetails getUserByUsername(String username) throws UsernameNotFoundException {

		User user = userDao.findUserByUsername(username);
		UserBuilder builder = null;
		if (user != null) {
      
			builder = org.springframework.security.core.userdetails.User.withUsername(username);
			builder.disabled(!user.isEnabled());
			builder.password(user.getPassword());
			String[] authorities = user.getAuthorities()
					.stream().map(a -> a.getAuthority()).toArray(String[]::new);

			builder.authorities(authorities);
    	} else {
    		throw new UsernameNotFoundException("User not found.");
    	}
    	return builder.build();
	}

}
