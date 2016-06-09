package com.catalystdevworks.mtidwell.timeclock.dao;

import com.catalystdevworks.mtidwell.timeclock.entity.Timeclock;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;


/**
 * Created by dsloane on 6/9/2016.
 */
@Repository
public class TimeclockDAO {

    private static final Logger logger = Logger.getLogger(TimeclockDAO.class);

    public static final String TABLE_NAME = "Timeclock";
    public static final String PRIMARY_KEY_NAME = Timeclock.COLUMN_ID;
}
