package com.catalystdevworks.mtidwell.timeclock.dao;

import com.catalystdevworks.mtidwell.timeclock.entity.Timeclock;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * Created by dsloane on 6/9/2016.
 */
@Repository
public class TimeclockDAO {

    private static final Logger logger = Logger.getLogger(TimeclockDAO.class);

    public static final String TABLE_NAME = "Timeclock";
    public static final String PRIMARY_KEY_NAME = Timeclock.COLUMN_ID;

    /**
     * <p>SQL to add new user into the Timeclock table.</p>
     *
     * <strong>{@value}</strong>
     */
    public static final String INSERT_TIMECLOCK = "INSERT INTO "+TABLE_NAME+" ("+PRIMARY_KEY_NAME+", "+Timeclock.COLUMN_USER_ID+", "+Timeclock.COLUMN_CLOCK_IN_TIME+", "+Timeclock.COLUMN_CLOCK_OUT_TIME+") VALUES(:"+PRIMARY_KEY_NAME+", :"+Timeclock.COLUMN_USER_ID+", :"+Timeclock.COLUMN_CLOCK_IN_TIME+", :"+Timeclock.COLUMN_CLOCK_OUT_TIME+")";
    /**
     * <p>SQL to retrieve all users from the Timeclock table.</p>
     *
     * <strong>{@value}</strong>
     */
    public static final String SELECT_ALL_TIMECLOCKS = "SELECT * FROM "+TABLE_NAME;

    /**
     * SQL to retrieve a list of timeclocks from the Timeclock table by id
     */
    public static final String SELECT_TIMECLOCKS_BY_USER = "SELECT * FROM " + TABLE_NAME + " WHERE "+ PRIMARY_KEY_NAME + "=:" + PRIMARY_KEY_NAME;

    /**
     * <p>SQL to retrieve a single timeclock from the Timeclock table by Timeclock id.</p>
     *
     * <strong>{@value}</strong>
     */
    public static final String SELECT_TIMECLOCK = "SELECT * FROM "+TABLE_NAME+" WHERE "+PRIMARY_KEY_NAME+"=:"+PRIMARY_KEY_NAME;
    /**
     * <p>SQL to update a single timeclock found by the current Timeclock id.</p>
     *
     * <strong>{@value}</strong>
     */
    public static final String UPDATE_TIMECLOCK = "UPDATE "+TABLE_NAME+" SET "+PRIMARY_KEY_NAME+"=:"+PRIMARY_KEY_NAME+", " +Timeclock.COLUMN_USER_ID+"=:"+Timeclock.COLUMN_USER_ID+", "+Timeclock.COLUMN_CLOCK_IN_TIME+"=:"+Timeclock.COLUMN_CLOCK_IN_TIME+", "+Timeclock.COLUMN_CLOCK_OUT_TIME + "=:" +Timeclock.COLUMN_CLOCK_OUT_TIME+" WHERE "+PRIMARY_KEY_NAME+"=:oldId";
    /**
     * <p>SQL to delete a timeclock record from the database.</p>
     *
     * <strong>{@value}</strong>
     */
    public static final String DELETE_TIMECLOCK = "DELETE FROM "+TABLE_NAME+" WHERE "+PRIMARY_KEY_NAME+"=:id";

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public Timeclock create(Timeclock timeclock) {

        if (logger.isDebugEnabled()) {
            logger.debug("Creating Timeclock:\n"+timeclock.toString());
        }
        jdbcTemplate.update(INSERT_TIMECLOCK, new MapSqlParameterSource(timeclock.toSQLMap()));

        return timeclock;
    }

    public List<Timeclock> getAll() {

        logger.debug("getting all timeclocks");
        return jdbcTemplate.query(SELECT_ALL_TIMECLOCKS, Timeclock.ROW_MAPPER);
    }

    public List<Timeclock> getByUser(UUID userId) {

        return jdbcTemplate.query(SELECT_TIMECLOCKS_BY_USER, new MapSqlParameterSource(Timeclock.COLUMN_USER_ID, userId.toString()), Timeclock.ROW_MAPPER);
    }

    public Timeclock get(Integer id) {
        return jdbcTemplate.queryForObject(SELECT_TIMECLOCK, new MapSqlParameterSource(PRIMARY_KEY_NAME, id), Timeclock.ROW_MAPPER);
    }

    public Timeclock update(Integer id, Timeclock timeclock) {

        Map<String, Object> parameters = timeclock.toSQLMap();
        parameters.put("oldId", id);

        jdbcTemplate.update(UPDATE_TIMECLOCK, new MapSqlParameterSource(parameters));

        return timeclock;
    }

    public void delete(Integer id) {
        jdbcTemplate.update(DELETE_TIMECLOCK, new MapSqlParameterSource(PRIMARY_KEY_NAME, id));
    }


    public NamedParameterJdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
