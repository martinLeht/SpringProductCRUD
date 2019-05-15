package com.mithrandir.springcrud.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.mithrandir.springcrud.entity.Authorities;
import com.mithrandir.springcrud.entity.User;


@Repository
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void createUser(User user) {
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setEnabled(1);
		
		Authorities authority = new Authorities();
		authority.setAuthority("ROLE_EMPLOYEE");
		authority.setUser(user);
		
		Session session = sessionFactory.getCurrentSession();
		
		session.save(user);
		session.save(authority);
	}

	@Override
	public User findUserByUsername(String username) {
		return null;
	}

}
