package com.apple.exercise.weatherforcast.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apple.exercise.weatherforcast.dto.WeatherDTO;
import com.apple.exercise.weatherforcast.dto.WeatherResponse;
import com.apple.exercise.weatherforcast.service.WeatherService;
import com.apple.exercise.weatherforcast.util.Util;

@RestController
@RequestMapping("api/weather")
@CrossOrigin(origins = "*")
public class WeatherController {

	@Autowired
	private WeatherService weatherService;
   
	
	@PostMapping
	@CrossOrigin(origins = "*")
	public void saveWeather(@RequestBody WeatherDTO weather) {
		 weatherService.saveWeather(weather);
	}
	
	@GetMapping(path = "{zip}")
	@CrossOrigin(origins = "*")
	public WeatherResponse getWeather(@PathVariable String zip){
		WeatherResponse response = new WeatherResponse();
		if(zip == null || zip=="null" || !Util.isNumeric(zip)) {
			System.out.println("Invalid ZipCode!");
			response.setStatus("FAILED");
			response.setMessage("Invalid ZipCode!");
			return response;
		}
		int zipCode = Integer.parseInt(zip);
     
		return weatherService.getWeather(zipCode);
	}
	

}
