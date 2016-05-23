package com.catalystdevworks.mtidwell.timeclock.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.catalystdevworks.mtidwell.timeclock.entity.User;
import com.catalystdevworks.mtidwell.timeclock.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping(method=RequestMethod.POST)
	public User create(@RequestBody User user) {
		return userService.create(user);
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
