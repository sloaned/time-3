package com.catalystdevworks.mtidwell.timeclock.dao;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.catalystdevworks.mtidwell.timeclock.entity.Role;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
	public static final String INSERT_USER = "INSERT INTO "+TABLE_NAME+" ("+PRIMARY_KEY_NAME+", "+User.COLUMN_FIRST_NAME+", "+User.COLUMN_LAST_NAME+", "+User.COLUMN_USERNAME+", "+User.COLUMN_PASSWORD+", "+User.COLUMN_ACTIVE+", "+User.COLUMN_ROLE+", "+User.COLUMN_CREATED_ON+", "+User.COLUMN_EMAIL+ ", "+User.COLUMN_ACCOUNT_LOCKED+ ", "+User.COLUMN_FAILED_LOGIN_ATTEMPTS+ ", "+User.COLUMN_LOGIN_TOKEN+") VALUES(:"+PRIMARY_KEY_NAME+", :"+User.COLUMN_FIRST_NAME+", :"+User.COLUMN_LAST_NAME+", :"+User.COLUMN_USERNAME+", :"+User.COLUMN_PASSWORD+", :"+User.COLUMN_ACTIVE+", :"+User.COLUMN_ROLE+", :"+User.COLUMN_CREATED_ON+", :"+User.COLUMN_EMAIL+", :"+User.COLUMN_ACCOUNT_LOCKED+", :"+User.COLUMN_FAILED_LOGIN_ATTEMPTS+", :"+User.COLUMN_LOGIN_TOKEN+")";
	/**
	 * <p>SQL to retrieve all users from the User table.</p>
	 * 
	 * <strong>{@value}</strong>
	 */
	public static final String SELECT_ALL_USERS = "SELECT * FROM "+TABLE_NAME;



	public static final String CHECK_USER_TOKEN = "SELECT * FROM " + TABLE_NAME + " WHERE "+ PRIMARY_KEY_NAME + "=:" + PRIMARY_KEY_NAME + " AND " + User.COLUMN_LOGIN_TOKEN + "=:" +User.COLUMN_LOGIN_TOKEN;

	/**
	 * SQL to retrieve a single user from the User table by username and password
	 */
	public static final String LOGIN_USER = "SELECT * FROM " + TABLE_NAME + " WHERE "+ User.COLUMN_USERNAME + "=:" + User.COLUMN_USERNAME + " AND " + User.COLUMN_PASSWORD + "=:" +User.COLUMN_PASSWORD;
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
	public static final String UPDATE_USER = "UPDATE "+TABLE_NAME+" SET "+PRIMARY_KEY_NAME+"=:"+PRIMARY_KEY_NAME+", "+User.COLUMN_FIRST_NAME+"=:"+User.COLUMN_FIRST_NAME+", "+User.COLUMN_LAST_NAME+"=:"+User.COLUMN_LAST_NAME+", "+User.COLUMN_USERNAME+"=:"+User.COLUMN_USERNAME+", "+User.COLUMN_PASSWORD+"=:"+User.COLUMN_PASSWORD+", "+User.COLUMN_ACTIVE+"=:"+User.COLUMN_ACTIVE+", "+User.COLUMN_ROLE+"=:"+User.COLUMN_ROLE+", "+User.COLUMN_CREATED_ON+"=:"+User.COLUMN_CREATED_ON+", "+User.COLUMN_EMAIL+"=:"+User.COLUMN_EMAIL+", "+User.COLUMN_ACCOUNT_LOCKED+"=:"+User.COLUMN_ACCOUNT_LOCKED+", "+User.COLUMN_FAILED_LOGIN_ATTEMPTS+"=:"+User.COLUMN_FAILED_LOGIN_ATTEMPTS+", "+User.COLUMN_LOGIN_TOKEN+"=:"+User.COLUMN_LOGIN_TOKEN+" WHERE "+PRIMARY_KEY_NAME+"=:oldId";
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
		user.setActive(true);
		user.setRole(Role.USER);
		user.setAccountLocked(false);
		user.setFailedLoginAttempts(0);
		System.out.println("creating user " + user.toString());
		if (logger.isDebugEnabled()) {
			logger.debug("Creating User:\n"+user.toString());
		}

		user.setPassword(sha256(user.getPassword()));

		user.setLoginToken(generateToken());
		
		jdbcTemplate.update(INSERT_USER, new MapSqlParameterSource(user.toSQLMap()));

		user.setPassword("");
		
		return user;
	}
	
	public List<User> read() {
		logger.debug("Retrieving All Users");
		return jdbcTemplate.query(SELECT_ALL_USERS, User.ROW_MAPPER);
	}

	public Boolean loggedIn(String userId, String token) {
		MapSqlParameterSource source = new MapSqlParameterSource();
		source.addValue(PRIMARY_KEY_NAME, userId);
		source.addValue(User.COLUMN_LOGIN_TOKEN, token);
		User user;

		logger.debug("in loggedIn, userId = " + userId + ", token = " + token);

		try {
			user = jdbcTemplate.queryForObject(CHECK_USER_TOKEN, source, User.ROW_MAPPER);
			logger.debug("returning true");
			return true;
		} catch (EmptyResultDataAccessException e) {
			logger.debug("returning false");
			return false;
		}
	}

	public User login(String username, String password) {
		logger.debug("Trying to login, username = " + username + ", password = " + password);
		MapSqlParameterSource source = new MapSqlParameterSource();
		password = sha256(password);
		source.addValue(User.COLUMN_USERNAME, username);
		source.addValue(User.COLUMN_PASSWORD, password);
		User user;

		try {
			user = jdbcTemplate.queryForObject(LOGIN_USER, source, User.ROW_MAPPER);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

		logger.debug("user = " + user.toString());

		user.setLoginToken(generateToken());

		update(user.getId(), user);

		user.setPassword("");
		return user;

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
		user.setPassword(sha256(user.getPassword()));
		Map<String, Object> parameters = user.toSQLMap();
		parameters.put("oldId", uuid.toString());
		
		jdbcTemplate.update(UPDATE_USER, new MapSqlParameterSource(parameters));

		user.setPassword("");
		
		return user;
	}

	public void delete(UUID uuid) {
		if (logger.isDebugEnabled()) {
			logger.debug("Deleting User: "+ uuid.toString());
		}
		jdbcTemplate.update(DELETE_USER, new MapSqlParameterSource(PRIMARY_KEY_NAME, uuid.toString()));
	}

	public String generateToken() {

		String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		SecureRandom rnd = new SecureRandom();

		int tokenLength = 30;
		StringBuilder sb = new StringBuilder( tokenLength );
		for( int i = 0; i < tokenLength; i++ ) {
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		}
		return sb.toString();
	}

	public String sha256(String base) {
		try{
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(base.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer();

			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if(hex.length() == 1) hexString.append('0');
				hexString.append(hex);
			}
			logger.debug("hash result = " + hexString.toString());
			return hexString.toString();
		} catch(Exception ex){
			throw new RuntimeException(ex);
		}
	}

	public NamedParameterJdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
