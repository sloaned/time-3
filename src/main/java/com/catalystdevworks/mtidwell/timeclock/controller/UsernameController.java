package com.catalystdevworks.mtidwell.timeclock.controller;

import com.catalystdevworks.mtidwell.timeclock.service.UsernameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dsloane on 6/8/2016.
 */
@RestController
@RequestMapping("/username")
public class UsernameController {
    @Autowired
    private UsernameService usernameService;


    @RequestMapping(method= RequestMethod.GET, path="/{username}")
    public Boolean checkUsername(@PathVariable String username) {
        return usernameService.checkUsername(username);
    }

    public UsernameService getUsernameService() {
        return usernameService;
    }

    public void setUsernameService(UsernameService userService) {
        this.usernameService = usernameService;
    }
}
