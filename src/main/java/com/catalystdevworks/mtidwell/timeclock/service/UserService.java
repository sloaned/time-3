package com.catalystdevworks.mtidwell.timeclock.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalystdevworks.mtidwell.timeclock.dao.UserDAO;
import com.catalystdevworks.mtidwell.timeclock.entity.User;

@Service
public class UserService {
	@Autowired
	private UserDAO userDAO;

	public User create(User user) {
		return userDAO.create(user);
	}
	
	public List<User> read() {
		return userDAO.read();
	}

	public User read(UUID uuid) {
		return userDAO.read(uuid);
	}

	public User update(UUID uuid, User user) {
		return userDAO.update(uuid, user);
	}

	public void delete(UUID uuid) {
		userDAO.delete(uuid);
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
}
