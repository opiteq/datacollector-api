package com.alwaysup.cartracker.trackingAPI.controller;

import com.alwaysup.cartracker.trackingAPI.model.DataPoint;
import com.alwaysup.cartracker.trackingAPI.repository.DataPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class DataPointController {
	
	@Autowired
	private DataPointRepository dataPointRepository;

	@GetMapping("/footprint")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	@GetMapping(value = "/list")
	public List<DataPoint> footprintdetails() {
	    
		List<DataPoint> userDetails = new ArrayList();
		for (DataPoint fp : dataPointRepository.findAll()) {
			userDetails.add(fp);
		}
		return userDetails; //new ResponseEntity(userDetails, HttpStatus.OK);
	}
}