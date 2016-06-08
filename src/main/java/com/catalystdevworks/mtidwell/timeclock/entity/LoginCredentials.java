package com.catalystdevworks.mtidwell.timeclock.entity;

import javax.validation.constraints.NotNull;

/**
 * Created by Dan on 6/7/2016.
 */
public class LoginCredentials {

    private String username;

    private String password;

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
