package com.catalystdevworks.mtidwell.timeclock.service;

import com.catalystdevworks.mtidwell.timeclock.dao.UsernameDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dsloane on 6/8/2016.
 */
@Service
public class UsernameService {
    @Autowired
    private UsernameDAO usernameDAO;

    public Boolean checkUsername(String username) { return usernameDAO.checkUsername(username); }


}
