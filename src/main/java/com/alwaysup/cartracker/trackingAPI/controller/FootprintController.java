package com.alwaysup.cartracker.trackingAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alwaysup.cartracker.trackingAPI.repository.FootprintRepository;
import com.alwaysup.cartracker.trackingAPI.model.Footprint;

@Controller
public class FootprintController {
	@Autowired
	private FootprintRepository footprintRepository;

	@PostMapping(path="/footprint")
	public @ResponseBody String addNewUser (@RequestParam(required=true,name="x") int x
			, @RequestParam(required=true,name="y") int y, @RequestParam(required=true,name="id") String userid) {
		Footprint n = new Footprint(userid, x, y);
		footprintRepository.save(n);
		return "0";
	}

	@GetMapping(path="/footprint")
	public @ResponseBody String addNewUser (@RequestParam(required=true,name="id") String userid) {
		Footprint[] steps = footprintRepository.findByuseridLike(userid);
		String[] jsonRepr = new String[steps.length];
		for (int i=0; i<steps.length; i++) {
			jsonRepr[i] = String.format("{\"x\":%f, \"y\":%f, \"time\":\"%s\"}",steps[i].getXcoord(), steps[i].getYcoord(), steps[i].getTimestamp().toString());
		}
		String response = "["+ String.join(",", jsonRepr) +"]";
		return response;
	}
}