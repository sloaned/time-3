package com.catalystdevworks.mtidwell.timeclock.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.catalystdevworks.mtidwell.timeclock.entity.LoginCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.catalystdevworks.mtidwell.timeclock.entity.User;
import com.catalystdevworks.mtidwell.timeclock.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping(method= RequestMethod.POST)
	public User create(@RequestBody User user) {
		return userService.create(user);
	}

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public User login(@RequestBody LoginCredentials credentials) {
		String username = credentials.getUsername();
		String password = credentials.getPassword();
		return userService.login(username, password);
	}

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public Boolean loggedIn(@RequestHeader("userId") String userId,
							@RequestHeader("token") String token) {

		return userService.loggedIn(userId, token);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<User> read() {
		return userService.read();
	}

	@RequestMapping(method=RequestMethod.GET, path="/{uuid}")
	public User read(@PathVariable("uuid") UUID uuid) {
		return userService.read(uuid);
	}

	@RequestMapping(method=RequestMethod.PUT, path="/{uuid}")
	public User update(@PathVariable("uuid") UUID uuid, @RequestBody User user) {
		return userService.update(uuid, user);
	}

	@RequestMapping(method=RequestMethod.DELETE, path="/{uuid}")
	@ResponseStatus(code=HttpStatus.OK)
	public void delete(@PathVariable("uuid") UUID uuid) {
		userService.delete(uuid);
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
