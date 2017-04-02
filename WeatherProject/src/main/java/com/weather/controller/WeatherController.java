package com.weather.controller;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.weather.model.CityWeatherCondition;
import com.weather.service.CityTemperatureService;

@Controller
public class WeatherController {

	@Autowired
	private CityTemperatureService cityTemperatureService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/weather/{city}")
	public ResponseEntity<CityWeatherCondition> getWeatherController(@PathVariable("city") String city) {
		try {
			CityWeatherCondition response = cityTemperatureService.connectWeatherService(city);
			
			if(response !=null)
			 return new ResponseEntity<CityWeatherCondition>(response, HttpStatus.OK);
		
			else
				return new ResponseEntity<CityWeatherCondition>(HttpStatus.BAD_REQUEST);
		} catch (JSONException e) {
			 return new ResponseEntity<CityWeatherCondition>(HttpStatus.BAD_REQUEST);
		}
	}

}
