package com.alwaysup.cartracker.trackingAPI.controller;

import com.alwaysup.cartracker.trackingAPI.model.DataPoint;
import com.alwaysup.cartracker.trackingAPI.repository.DataPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DataPointController {

    @Autowired
    private DataPointRepository dataPointRepository;

    @GetMapping("/datapoint")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping(value = "/list")
    public Page<DataPoint> allDataPoints() {

        Page<DataPoint> userDetails = null;
        return userDetails;
    }
}