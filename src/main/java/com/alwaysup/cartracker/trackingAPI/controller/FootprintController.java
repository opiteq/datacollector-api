package com.alwaysup.cartracker.trackingAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alwaysup.cartracker.trackingAPI.dao.FootprintDAO;

 
@RestController
public class FootprintController {
	
	@Autowired
	private FootprintDAO footprintDAO;

	@RequestMapping("/footprint")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity footprintdetails() {
	    
		List userDetails = footprintDAO.getAll();
		return new ResponseEntity(userDetails, HttpStatus.OK);
	}
}