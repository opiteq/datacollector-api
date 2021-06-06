package com.alwaysup.tracker.api.controller;

import com.alwaysup.tracker.api.service.DataPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DataPointController {

    @Autowired
    private DataPointService dataPointService;

    @GetMapping("/datapoint")
    public String index() {
        return "Greetings from Spring Boot!";
    }

//    @GetMapping(value = "/list")
//    public List<DataPoint> allDataPoints(
//            @RequestParam String imei
//    ) {
//        List<DataPoint> userDetails = dataPointService.getAllDataPointsByDeviceAndDataTypeBetweenTimes(deviceId, dataType, startTimestamp, endTimestamp, page, size);
//        return userDetails;
//    }
}