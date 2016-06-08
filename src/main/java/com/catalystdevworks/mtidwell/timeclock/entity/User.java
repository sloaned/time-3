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
	
	private UUID id;

	//@NotNull(message="First name is required")
	private String firstName;

	//@NotNull(message="Last name is required")
	private String lastName;

	//@NotNull(message="Username is required")
	private String username;

	//@NotNull(message="Password is required")
	private String password;

	//@NotNull(message="User must be active or inactive")
	private Boolean active;

	//@NotNull
	@Enumerated(value = EnumType.STRING)
	private Role role;

	@JsonFormat(shape=JsonFormat.Shape.STRING)
	private ZonedDateTime createdOn;

	@JsonFormat(shape=JsonFormat.Shape.STRING)
	private LocalDate birthday;
	
	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setId(UUID.fromString(rs.getString(COLUMN_ID)));
		user.setFirstName(rs.getString(COLUMN_FIRST_NAME));
		user.setLastName(rs.getString(COLUMN_LAST_NAME));
		user.setUsername(rs.getString(COLUMN_USERNAME));
		user.setPassword(rs.getString(COLUMN_PASSWORD));
		user.setActive(rs.getBoolean(COLUMN_ACTIVE));
		user.setRole(Role.valueOf(rs.getString(COLUMN_ROLE)));
		user.setBirthday(DateTimeFormatter.ISO_DATE.parse(rs.getString(COLUMN_BIRTHDAY), LocalDate::from));
		user.setCreatedOn(DateTimeFormatter.ISO_DATE_TIME.parse(rs.getString(COLUMN_CREATED_ON), ZonedDateTime::from));
		
		return user;
	}

	public Map<String, Object> toSQLMap() {
		Map<String, Object> sqlData = new HashMap<>();
		
		sqlData.put(COLUMN_ID, id.toString());
		sqlData.put(COLUMN_FIRST_NAME, firstName);
		sqlData.put(COLUMN_LAST_NAME, lastName);
		sqlData.put(COLUMN_USERNAME, username);
		sqlData.put(COLUMN_PASSWORD, password);
		sqlData.put(COLUMN_ACTIVE, active);
		sqlData.put(COLUMN_ROLE, role.toString());
		sqlData.put(COLUMN_BIRTHDAY, DateTimeFormatter.ISO_DATE.format(birthday));
		sqlData.put(COLUMN_CREATED_ON, DateTimeFormatter.ISO_DATE_TIME.format(createdOn.withZoneSameInstant(ZoneOffset.UTC)));
		
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
}
