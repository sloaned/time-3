package com.catalystdevworks.mtidwell.timeclock.dao;

import com.catalystdevworks.mtidwell.timeclock.entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by dsloane on 6/8/2016.
 */
@Repository
public class UsernameDAO {

    private static final Logger logger = Logger.getLogger(UserDAO.class);

    public static final String TABLE_NAME = "User";

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    /**
     * <p>SQL to retrieve a single user from the User table by username.</p>
     *
     * <strong>{@value}</strong>
    */
    public static final String SELECT_USER = "SELECT * FROM "+TABLE_NAME+" WHERE "+ User.COLUMN_USERNAME + "=:" +User.COLUMN_USERNAME;

    public Boolean checkUsername(String username) {
        logger.debug("checking if username is taken, username = " + username);
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue(User.COLUMN_USERNAME, username);

        User user;
        try {

            user = jdbcTemplate.queryForObject(SELECT_USER, source, User.ROW_MAPPER);
            logger.debug("got a user, username = " + user.getUsername());
            return true;
        } catch (EmptyResultDataAccessException e) {
            logger.debug("got the exception");
            return false;
        }
    }

    public NamedParameterJdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

}
