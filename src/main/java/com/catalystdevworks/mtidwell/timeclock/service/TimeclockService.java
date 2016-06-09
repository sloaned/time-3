package com.catalystdevworks.mtidwell.timeclock.service;

import com.catalystdevworks.mtidwell.timeclock.entity.Timeclock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by dsloane on 6/9/2016.
 */
@Service
public class TimeclockService {

    @Autowired
    private TimeclockDAO timeclockDAO;

    public Timeclock create(Timeclock timeclock) { return timeclockDAO.create(timeclock); }

    public List<Timeclock> getAll() { return timeclockDAO.getAll(); }

    public List<Timeclock> getByUser(UUID userId) { return timeclockDAO.getByUser(userId); }

    public Timeclock get(Integer id) { return timeclockDAO.get(id); }

    public Timeclock update(Integer id, Timeclock timeclock) { return timeclockDAO.update(id, timeclock); }

    public void delete(Integer id) { timeclockDAO.delete(id); }
}
