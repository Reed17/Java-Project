package com.weather.service;

import java.text.DecimalFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.weather.model.CityWeatherCondition;
import com.weather.model.Weather;
import com.weather.repository.CityWeatherConditionRepository;

@Service
public class CityTemperatureService {

	@Autowired
	private CityWeatherConditionRepository cityWeatherConditionRepository;

	public CityWeatherCondition connectWeatherService(String city) throws JSONException {

		final String uri = "http://api.openweathermap.org/data/2.5/weather?q=" + city
				+ "&APPID=622d9f0e01b510ff8ee4f7ac4a644fda";
		ResponseEntity<String> personEntity = null;

		RestTemplate restTemplate = new RestTemplate();
		try {
			personEntity = restTemplate.getForEntity(uri, String.class);
		} catch (HttpClientErrorException e) {
				return null;
		}
		CityWeatherCondition weatherCondition = new CityWeatherCondition();
		JSONObject weatherJson = new JSONObject(personEntity.getBody());
		weatherCondition.setLatitud(weatherJson.getJSONObject("coord").getDouble("lon"));
		weatherCondition.setLongitud(weatherJson.getJSONObject("coord").getDouble("lat"));

		JSONArray weatherArray = weatherJson.getJSONArray("weather");

		for (int i = 0; i < weatherArray.length(); i++) {
			Weather weather = new Weather();
			JSONObject elements = weatherArray.getJSONObject(i);
			weather.setMain(elements.getString("main"));
			weather.setDescription(elements.getString("description"));
			weatherCondition.getWeather().add(weather);
		}
		DecimalFormat df = new DecimalFormat("#.##");

		double temperature = weatherJson.getJSONObject("main").getDouble("temp") - 273.15;
		temperature = Double.valueOf(df.format(temperature));
		weatherCondition.setTemperature(temperature);

		weatherCondition.setHumidity(weatherJson.getJSONObject("clouds").getDouble("all"));

		double temperatureMin = weatherJson.getJSONObject("main").getDouble("temp_min") - 273.15;
		temperatureMin = Double.valueOf(df.format(temperatureMin));
		weatherCondition.setTemperatureMin(temperatureMin);

		double temperatureMax = weatherJson.getJSONObject("main").getDouble("temp_max") - 273.15;
		temperatureMax = Double.valueOf(df.format(temperatureMax));
		weatherCondition.setTemperatureMax(temperatureMax);

		weatherCondition.setWindSpeed(weatherJson.getJSONObject("wind").getDouble("speed"));
		weatherCondition.setCountry(weatherJson.getJSONObject("sys").getString("country"));
		weatherCondition.setCity(weatherJson.getString("name"));
		weatherCondition.setDate(new Date());

		CityWeatherCondition cityWeatherCondition = cityWeatherConditionRepository.save(weatherCondition);
		return cityWeatherCondition;
	}
}
