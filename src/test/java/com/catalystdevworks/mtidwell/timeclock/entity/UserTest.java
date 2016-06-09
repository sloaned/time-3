package com.catalystdevworks.mtidwell.timeclock.entity;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.anyString;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

public class UserTest {
	private User user;
	
	private ResultSet mockResultSet;
	
	private UUID testId = UUID.randomUUID();
	private String testFirstName = "Test";
	private String testLastName = "Name";
	private String testUsername = "testname";
	private String testPassword = "password1";
	private Boolean testActive = true;
	private Role testRole = Role.USER;
	//private LocalDate testBirthday = LocalDate.of(2016, 01, 01);
	private ZonedDateTime testCreatedOn = ZonedDateTime.of(LocalDate.of(2016, 01, 01), LocalTime.of(0, 0, 0, 0), ZoneOffset.UTC);
	
	@Before
	public void setup() throws Exception {
		user = new User();
		
		//user.setBirthday(testBirthday);
		user.setCreatedOn(testCreatedOn);
		user.setId(testId);
		user.setFirstName(testFirstName);
		user.setLastName(testLastName);
		user.setUsername(testUsername);
		user.setPassword(testPassword);
		user.setActive(testActive);
		user.setRole(testRole);
		
		mockResultSet = mock(ResultSet.class);
		when(mockResultSet.getString(User.COLUMN_ID)).thenReturn(testId.toString());
		when(mockResultSet.getString(User.COLUMN_FIRST_NAME)).thenReturn(testFirstName);
		when(mockResultSet.getString(User.COLUMN_LAST_NAME)).thenReturn(testLastName);
		when(mockResultSet.getString(User.COLUMN_USERNAME)).thenReturn(testUsername);
		when(mockResultSet.getString(User.COLUMN_PASSWORD)).thenReturn(testPassword);
		when(mockResultSet.getBoolean(User.COLUMN_ACTIVE)).thenReturn(testActive);
		when(mockResultSet.getString(User.COLUMN_ROLE)).thenReturn(testRole.toString());
		when(mockResultSet.getString(User.COLUMN_CREATED_ON)).thenReturn(DateTimeFormatter.ISO_DATE_TIME.format(testCreatedOn.withZoneSameInstant(ZoneOffset.UTC)));
		//when(mockResultSet.getString(User.COLUMN_BIRTHDAY)).thenReturn(DateTimeFormatter.ISO_DATE.format(testBirthday));
	}
	
	@Test
	public void testToSQLMap() {
		Map<String, Object> actual = user.toSQLMap();
		
		assertEquals("Id was not set in the map.", testId.toString(), actual.get(User.COLUMN_ID));
		assertEquals("First Name was not set in the map.", testFirstName, actual.get(User.COLUMN_FIRST_NAME));
		assertEquals("Last Name was not set in the map.", testLastName, actual.get(User.COLUMN_LAST_NAME));
		assertEquals("Username was not set in the map.", testUsername, actual.get(User.COLUMN_USERNAME));
		assertEquals("Password was not set in the map.", testPassword, actual.get(User.COLUMN_PASSWORD));
		assertEquals("Active was not set in the map", testActive, actual.get(User.COLUMN_ACTIVE));
		assertEquals("Role was not set in the map", testRole.toString(), (actual.get(User.COLUMN_ROLE)).toString());

		//assertEquals("Birthday was not set in the map.", DateTimeFormatter.ISO_DATE.format(testBirthday), actual.get(User.COLUMN_BIRTHDAY));
		assertEquals("Id was not set in the map.", DateTimeFormatter.ISO_DATE_TIME.format(testCreatedOn.withZoneSameInstant(ZoneOffset.UTC)), actual.get(User.COLUMN_CREATED_ON));
	}
	
	@Test
	public void testMapRow() throws Exception {
		User actual = User.ROW_MAPPER.mapRow(mockResultSet, 0);
		
		assertEquals("Id was not set in the user object.", testId, actual.getId());
		assertEquals("First Name was not set in the user object.", testFirstName, actual.getFirstName());
		assertEquals("Last Name was not set in the user object.", testLastName, actual.getLastName());
		assertEquals("Username was not set in the user object.", testUsername, actual.getUsername());
		assertEquals("Password was not set in the user object", testPassword, actual.getPassword());
		assertEquals("Active was not set in the user object", testActive, actual.isActive());
		assertEquals("Role was not set in the user object", testRole, actual.getRole());
		//assertEquals("Birthday was not set in the user object.", testBirthday, actual.getBirthday());
		assertEquals("Id was not set in the user object.", testCreatedOn, actual.getCreatedOn());
	}
	
	@Test(expected=SQLException.class)
	public void testMapRow_SQLException() throws Exception {
		mockResultSet = mock(ResultSet.class);
		when(mockResultSet.getString(anyString())).thenThrow(new SQLException());
		
		User.ROW_MAPPER.mapRow(mockResultSet, 0);
	}
}
