package com.catalystdevworks.mtidwell.timeclock.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.jdbc.core.RowMapper;

import com.fasterxml.jackson.annotation.JsonFormat;

public class User implements RowMapper<User>{
	
	public static final RowMapper<User> ROW_MAPPER = new User();
	
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_BIRTHDAY = "birthday";
	public static final String COLUMN_CREATED_ON = "createdOn";
	public static final String COLUMN_FIRST_NAME = "firstName";
	public static final String COLUMN_LAST_NAME = "lastName";
	public static final String COLUMN_USERNAME = "username";
	public static final String COLUMN_PASSWORD = "password";
	public static final String COLUMN_ACTIVE = "active";
	public static final String COLUMN_ROLE = "role";
	public static final String COLUMN_EMAIL = "email";
	public static final String COLUMN_ACCOUNT_LOCKED = "accountLocked";
	public static final String COLUMN_FAILED_LOGIN_ATTEMPTS = "failedLoginAttempts";
	public static final String COLUMN_LOGIN_TOKEN = "loginToken";
	
	private UUID id;

	//@NotNull(message="First name is required")
	private String firstName;

	//@NotNull(message="Last name is required")
	private String lastName;

	//@NotNull(message="Username is required")
	private String username;

	//@NotNull(message="Password is required")
	private String password;

	private String email;

	//@NotNull(message="User must be active or inactive")
	private Boolean active;

	//@NotNull
	@Enumerated(value = EnumType.STRING)
	private Role role;

	@JsonFormat(shape=JsonFormat.Shape.STRING)
	private ZonedDateTime createdOn;

	@JsonFormat(shape=JsonFormat.Shape.STRING)
	private LocalDate birthday;

	private Boolean accountLocked;

	private Integer failedLoginAttempts;

	private String loginToken;
	
	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setId(UUID.fromString(rs.getString(COLUMN_ID)));
		user.setFirstName(rs.getString(COLUMN_FIRST_NAME));
		user.setLastName(rs.getString(COLUMN_LAST_NAME));
		user.setUsername(rs.getString(COLUMN_USERNAME));
		user.setPassword(rs.getString(COLUMN_PASSWORD));
		user.setEmail(rs.getString(COLUMN_EMAIL));
		user.setActive(rs.getBoolean(COLUMN_ACTIVE));
		user.setRole(Role.valueOf(rs.getString(COLUMN_ROLE)));
		user.setBirthday(DateTimeFormatter.ISO_DATE.parse(rs.getString(COLUMN_BIRTHDAY), LocalDate::from));
		user.setCreatedOn(DateTimeFormatter.ISO_DATE_TIME.parse(rs.getString(COLUMN_CREATED_ON), ZonedDateTime::from));
		user.setAccountLocked(rs.getBoolean(COLUMN_ACCOUNT_LOCKED));
		user.setFailedLoginAttempts((rs.getInt(COLUMN_FAILED_LOGIN_ATTEMPTS)));
		user.setLoginToken(rs.getString(COLUMN_LOGIN_TOKEN));
		
		return user;
	}

	public Map<String, Object> toSQLMap() {
		Map<String, Object> sqlData = new HashMap<>();
		
		sqlData.put(COLUMN_ID, id.toString());
		sqlData.put(COLUMN_FIRST_NAME, firstName);
		sqlData.put(COLUMN_LAST_NAME, lastName);
		sqlData.put(COLUMN_USERNAME, username);
		sqlData.put(COLUMN_PASSWORD, password);
		sqlData.put(COLUMN_EMAIL, email);
		sqlData.put(COLUMN_ACTIVE, active);
		sqlData.put(COLUMN_ROLE, role.toString());
		sqlData.put(COLUMN_BIRTHDAY, DateTimeFormatter.ISO_DATE.format(birthday));
		sqlData.put(COLUMN_CREATED_ON, DateTimeFormatter.ISO_DATE_TIME.format(createdOn.withZoneSameInstant(ZoneOffset.UTC)));
		sqlData.put(COLUMN_ACCOUNT_LOCKED, accountLocked);
		sqlData.put(COLUMN_FAILED_LOGIN_ATTEMPTS, failedLoginAttempts);
		sqlData.put(COLUMN_LOGIN_TOKEN, loginToken);
		
		return sqlData;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
	}
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getFirstName() { return firstName; }
	public void setFirstName(String firstName) { this.firstName = firstName; }
	public String getLastName() { return lastName; }
	public void setLastName(String lastName) { this.lastName = lastName; }
	public String getUsername() { return username; }
	public void setUsername(String username) { this.username = username; }
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	public Boolean isActive() { return active; }
	public void setActive(Boolean active) { this.active = active; }
	public Role getRole() { return role; }
	public void setRole(Role role) { this.role = role; }

	public ZonedDateTime getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(ZonedDateTime createdOn) {
		this.createdOn = createdOn;
	}
	public LocalDate getBirthday() {
		return birthday;
	}
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	public Boolean isAccountLocked() { return accountLocked; }
	public void setAccountLocked(Boolean accountLocked) { this.accountLocked = accountLocked; }
	public Integer getFailedLoginAttempts() { return failedLoginAttempts; }
	public void setFailedLoginAttempts(Integer failedLoginAttempts) { this.failedLoginAttempts = failedLoginAttempts; }
	public String getLoginToken() { return loginToken; }
	public void setLoginToken(String loginToken) { this.loginToken = loginToken; }
}
