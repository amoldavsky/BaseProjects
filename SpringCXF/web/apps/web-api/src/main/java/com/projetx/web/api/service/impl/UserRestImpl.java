package com.projetx.web.api.service.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetx.logic.api.service.UserService;
import com.projetx.sdk.user.User;
import com.projetx.sdk.user.impl.BasicUser;
import com.projetx.web.api.service.JaxrsService;
import com.projetx.web.api.service.UserRest;

/**
 * Actual implementation of the UserRest interface, which contains the
 * actual CXF annotations.
 * 
 * @author Assaf Moldavsky
 *
 */
@JaxrsService
@Service("com.projetx.web.api.service.UserRest")
public class UserRestImpl implements UserRest {
	
	@Autowired
	UserService userService;
	
	@Override
	public User getUser( Integer userId ) {
		return userService.getUser( userId );
	}
	
	@Override
	public User createUser(String firstName, String lastName, String email, String password) {
		User user = new BasicUser();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPassword(password);
		userService.createUser((com.projetx.logic.api.data.User) user);
		return user;
	}
	
}
