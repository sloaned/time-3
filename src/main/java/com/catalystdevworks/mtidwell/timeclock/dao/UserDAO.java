package com.catalystdevworks.mtidwell.timeclock.dao;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.catalystdevworks.mtidwell.timeclock.entity.User;

@Repository
public class UserDAO {
	private static final Logger logger = Logger.getLogger(UserDAO.class);
	
	public static final String TABLE_NAME = "User";
	public static final String PRIMARY_KEY_NAME = User.COLUMN_ID;
	
	/**
	 * <p>SQL to add new user into the User table.</p>
	 * 
	 * <strong>{@value}</strong>
	 */
	public static final String INSERT_USER = "INSERT INTO "+TABLE_NAME+" ("+PRIMARY_KEY_NAME+", "+User.COLUMN_FIRST_NAME+", "+User.COLUMN_LAST_NAME+", "+User.COLUMN_USERNAME+", "+User.COLUMN_PASSWORD+", "+User.COLUMN_ACTIVE+", "+User.COLUMN_ROLE+", "+User.COLUMN_CREATED_ON+", "+User.COLUMN_BIRTHDAY+") VALUES(:"+PRIMARY_KEY_NAME+", :"+User.COLUMN_FIRST_NAME+", :"+User.COLUMN_LAST_NAME+", :"+User.COLUMN_USERNAME+", :"+User.COLUMN_PASSWORD+", :"+User.COLUMN_ACTIVE+", :"+User.COLUMN_ROLE+", :"+User.COLUMN_CREATED_ON+", :"+User.COLUMN_BIRTHDAY+")";
	/**
	 * <p>SQL to retrieve all users from the User table.</p>
	 * 
	 * <strong>{@value}</strong>
	 */
	public static final String SELECT_ALL_USERS = "SELECT * FROM "+TABLE_NAME;


	public static final String LOGIN_USER = "SELECT * FROM " + TABLE_NAME + " WHERE "+ User.COLUMN_USERNAME + "=:" + User.COLUMN_FIRST_NAME + " AND " + User.COLUMN_PASSWORD + "=:" +User.COLUMN_PASSWORD;
	/**
	 * <p>SQL to retrieve a single user from the User table by User id.</p>
	 * 
	 * <strong>{@value}</strong>
	 */
	public static final String SELECT_USER = "SELECT * FROM "+TABLE_NAME+" WHERE "+PRIMARY_KEY_NAME+"=:"+PRIMARY_KEY_NAME;
	/**
	 * <p>SQL to update a single user found the the current User id.</p>
	 * 
	 * <strong>{@value}</strong>
	 */
	public static final String UPDATE_USER = "UPDATE "+TABLE_NAME+" SET "+PRIMARY_KEY_NAME+"=:"+PRIMARY_KEY_NAME+", "+User.COLUMN_FIRST_NAME+"=:"+User.COLUMN_FIRST_NAME+", "+User.COLUMN_LAST_NAME+"=:"+User.COLUMN_LAST_NAME+", "+User.COLUMN_USERNAME+"=:"+User.COLUMN_USERNAME+", "+User.COLUMN_PASSWORD+"=:"+User.COLUMN_PASSWORD+", "+User.COLUMN_ACTIVE+"=:"+User.COLUMN_ACTIVE+", "+User.COLUMN_ROLE+"=:"+User.COLUMN_ROLE+", "+User.COLUMN_CREATED_ON+"=:"+User.COLUMN_CREATED_ON+", "+User.COLUMN_BIRTHDAY+"=:"+User.COLUMN_BIRTHDAY+" WHERE "+PRIMARY_KEY_NAME+"=:oldId";
	/**
	 * <p>SQL to delete a user record from the database.</p>
	 * 
	 * <strong>{@value}</strong>
	 */
	public static final String DELETE_USER = "DELETE FROM "+TABLE_NAME+" WHERE "+PRIMARY_KEY_NAME+"=:id";
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public User create(User user) {
		user.setId(UUID.randomUUID());
		user.setCreatedOn(ZonedDateTime.now(ZoneOffset.UTC));
		System.out.println("creating user " + user.toString());
		if (logger.isDebugEnabled()) {
			logger.debug("Creating User:\n"+user.toString());
		}
		
		jdbcTemplate.update(INSERT_USER, new MapSqlParameterSource(user.toSQLMap()));
		
		return user;
	}
	
	public List<User> read() {
		logger.debug("Retrieving All Users");
		return jdbcTemplate.query(SELECT_ALL_USERS, User.ROW_MAPPER);
	}

	public Boolean login(String username, String password) {
		logger.debug("Trying to login");
		MapSqlParameterSource source = new MapSqlParameterSource();
		source.addValue(User.COLUMN_USERNAME, username);
		source.addValue(User.COLUMN_PASSWORD, password);
		User user = jdbcTemplate.queryForObject(LOGIN_USER, source, User.ROW_MAPPER);

		System.out.println("user = " + user.toString());
		logger.debug("user = " + user.toString());
		if (user != null) {
			return true;
		}
		return false;
	}

	public User read(UUID uuid) {
		if (logger.isDebugEnabled()) {
			logger.debug("Retrieving User: "+ uuid.toString());
		}
		return jdbcTemplate.queryForObject(SELECT_USER, new MapSqlParameterSource(PRIMARY_KEY_NAME, uuid.toString()), User.ROW_MAPPER);
	}

	public User update(UUID uuid, User user) {
		if (logger.isDebugEnabled()) {
			logger.debug("Updating User: "+ uuid.toString() + "\n" + user.toString());
		}
		
		Map<String, Object> parameters = user.toSQLMap();
		parameters.put("oldId", uuid.toString());
		
		jdbcTemplate.update(UPDATE_USER, new MapSqlParameterSource(parameters));
		
		return user;
	}

	public void delete(UUID uuid) {
		if (logger.isDebugEnabled()) {
			logger.debug("Deleting User: "+ uuid.toString());
		}
		jdbcTemplate.update(DELETE_USER, new MapSqlParameterSource(PRIMARY_KEY_NAME, uuid.toString()));
	}

	public NamedParameterJdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
