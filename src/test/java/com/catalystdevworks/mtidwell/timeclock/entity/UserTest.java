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
	private String testFullName = "Test Full Name";
	private LocalDate testBirthday = LocalDate.of(2016, 01, 01);
	private ZonedDateTime testCreatedOn = ZonedDateTime.of(LocalDate.of(2016, 01, 01), LocalTime.of(0, 0, 0, 0), ZoneOffset.UTC);
	
	@Before
	public void setup() throws Exception {
		user = new User();
		
		user.setBirthday(testBirthday);
		user.setCreatedOn(testCreatedOn);
		user.setId(testId);
		user.setFullName(testFullName);
		
		mockResultSet = mock(ResultSet.class);
		when(mockResultSet.getString(User.COLUMN_ID)).thenReturn(testId.toString());
		when(mockResultSet.getString(User.COLUMN_FULL_NAME)).thenReturn(testFullName);
		when(mockResultSet.getString(User.COLUMN_CREATED_ON)).thenReturn(DateTimeFormatter.ISO_DATE_TIME.format(testCreatedOn.withZoneSameInstant(ZoneOffset.UTC)));
		when(mockResultSet.getString(User.COLUMN_BIRTHDAY)).thenReturn(DateTimeFormatter.ISO_DATE.format(testBirthday));
	}
	
	@Test
	public void testToSQLMap() {
		Map<String, Object> actual = user.toSQLMap();
		
		assertEquals("Id was not set in the map.", testId.toString(), actual.get(User.COLUMN_ID));
		assertEquals("Full Name was not set in the map.", testFullName, actual.get(User.COLUMN_FULL_NAME));
		assertEquals("Birthday was not set in the map.", DateTimeFormatter.ISO_DATE.format(testBirthday), actual.get(User.COLUMN_BIRTHDAY));
		assertEquals("Id was not set in the map.", DateTimeFormatter.ISO_DATE_TIME.format(testCreatedOn.withZoneSameInstant(ZoneOffset.UTC)), actual.get(User.COLUMN_CREATED_ON));
	}
	
	@Test
	public void testMapRow() throws Exception {
		User actual = User.ROW_MAPPER.mapRow(mockResultSet, 0);
		
		assertEquals("Id was not set in the user object.", testId, actual.getId());
		assertEquals("Full Name was not set in the user object.", testFullName, actual.getFullName());
		assertEquals("Birthday was not set in the user object.", testBirthday, actual.getBirthday());
		assertEquals("Id was not set in the user object.", testCreatedOn, actual.getCreatedOn());
	}
	
	@Test(expected=SQLException.class)
	public void testMapRow_SQLException() throws Exception {
		mockResultSet = mock(ResultSet.class);
		when(mockResultSet.getString(anyString())).thenThrow(new SQLException());
		
		User.ROW_MAPPER.mapRow(mockResultSet, 0);
	}
}
