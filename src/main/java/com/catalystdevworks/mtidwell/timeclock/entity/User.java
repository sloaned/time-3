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

import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.jdbc.core.RowMapper;

import com.fasterxml.jackson.annotation.JsonFormat;

public class User implements RowMapper<User>{
	
	public static final RowMapper<User> ROW_MAPPER = new User();
	
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_FULL_NAME = "fullName";
	public static final String COLUMN_BIRTHDAY = "birthday";
	public static final String COLUMN_CREATED_ON = "createdOn";
	
	private UUID id;
	
	@Size(min=3, max=30)
	private String fullName;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	private ZonedDateTime createdOn;

	@JsonFormat(shape=JsonFormat.Shape.STRING)
	private LocalDate birthday;
	
	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		
		user.setId(UUID.fromString(rs.getString(COLUMN_ID)));
		user.setFullName(rs.getString(COLUMN_FULL_NAME));
		user.setBirthday(DateTimeFormatter.ISO_DATE.parse(rs.getString(COLUMN_BIRTHDAY), LocalDate::from));
		user.setCreatedOn(DateTimeFormatter.ISO_DATE_TIME.parse(rs.getString(COLUMN_CREATED_ON), ZonedDateTime::from));
		
		return user;
	}

	public Map<String, Object> toSQLMap() {
		Map<String, Object> sqlData = new HashMap<>();
		
		sqlData.put(COLUMN_ID, id.toString());
		sqlData.put(COLUMN_FULL_NAME, fullName);
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
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
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
