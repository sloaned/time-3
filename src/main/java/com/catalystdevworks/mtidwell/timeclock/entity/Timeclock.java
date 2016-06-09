package com.catalystdevworks.mtidwell.timeclock.entity;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by dsloane on 6/9/2016.
 */
public class Timeclock implements RowMapper<Timeclock> {

    public static final RowMapper<Timeclock> ROW_MAPPER = new Timeclock();

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USER_ID = "userId";
    public static final String COLUMN_CLOCK_IN_TIME = "clockInTime";
    public static final String COLUMN_CLOCK_OUT_TIME = "clockOutTime";

    private Integer id;
    private UUID userId;
    private Long clockInTime;
    private Long clockOutTime;

    @Override
    public Timeclock mapRow(ResultSet rs, int rowNum) throws SQLException {
        Timeclock clock = new Timeclock();
        clock.setId(rs.getInt(COLUMN_ID));
        clock.setUserId(UUID.fromString(rs.getString(COLUMN_USER_ID)));
        clock.setClockInTime(rs.getLong(COLUMN_CLOCK_IN_TIME));
        clock.setClockOutTime(rs.getLong(COLUMN_CLOCK_OUT_TIME));

        return clock;
    }

    public Map<String, Object> toSQLMap() {
        Map<String, Object> sqlData = new HashMap<>();
        sqlData.put(COLUMN_ID, id);
        sqlData.put(COLUMN_USER_ID, userId.toString());
        sqlData.put(COLUMN_CLOCK_IN_TIME, clockInTime);
        sqlData.put(COLUMN_CLOCK_OUT_TIME, clockOutTime);

        return sqlData;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }
    public Long getClockInTime() { return clockInTime; }
    public void setClockInTime(Long clockInTime) { this.clockInTime = clockInTime; }
    public Long getClockOutTime() { return clockOutTime; }
    public void setClockOutTime(Long clockOutTime) { this.clockOutTime = clockOutTime; }

}
