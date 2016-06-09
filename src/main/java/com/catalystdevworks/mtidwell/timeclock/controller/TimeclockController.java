package com.catalystdevworks.mtidwell.timeclock.controller;

import com.catalystdevworks.mtidwell.timeclock.entity.Timeclock;
import com.catalystdevworks.mtidwell.timeclock.service.TimeclockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Created by dsloane on 6/9/2016.
 */
@RestController
@RequestMapping("/timeclock")
public class TimeclockController {

    @Autowired
    private TimeclockService timeclockService;

    @RequestMapping(method= RequestMethod.POST)
    public Timeclock create(@RequestBody Timeclock timeclock) {
        return timeclockService.create(timeclock);
    }

    @RequestMapping(method= RequestMethod.GET)
    public List<Timeclock> getAll() {
        return timeclockService.getAll();
    }

    @RequestMapping(method=RequestMethod.GET, path="/user/{id}")
    public Timeclock get(@PathVariable Integer id) {
        return timeclockService.get(id);
    }

    @RequestMapping(method=RequestMethod.GET, path="/user/{uuid}")
    public List<Timeclock> getByUser(@PathVariable("uuid") UUID userId) {
        return timeclockService.getByUser(userId);
    }

    @RequestMapping(method=RequestMethod.PUT, path="/{id}")
    public Timeclock update(@PathVariable Integer id, @RequestBody Timeclock timeclock) {
        return timeclockService.update(id, timeclock);
    }

    @RequestMapping(method=RequestMethod.DELETE, path="/{id}")
    @ResponseStatus(code= HttpStatus.OK)
    public void delete(@PathVariable Integer id) {
        timeclockService.delete(id);
    }

    public TimeclockService getTimeclockService() { return timeclockService; }

    public void setTimeclockService(TimeclockService timeclockService) { this.timeclockService = timeclockService; }

}
