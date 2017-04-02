package com.weather.service;

import static org.mockito.Mockito.when;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.weather.model.CityWeatherCondition;
import com.weather.repository.CityWeatherConditionRepository;

public class CityTemperatureServiceTest {

	@InjectMocks
	private CityTemperatureService cityTemperatureService;
	
	@Mock
	private CityWeatherConditionRepository cityWeatherConditionRepository;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void connectWeatherServiceTest() throws JSONException
	{
		CityWeatherCondition cityWeatherCondition = new CityWeatherCondition();
		when(cityTemperatureService.connectWeatherService("london")).thenReturn(cityWeatherCondition);
	}
	
}
