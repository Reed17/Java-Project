package com.weather.repository;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.View;
import com.weather.model.CityWeatherCondition;
import com.weather.model.Weather;

public class CityWeatherConditionRepositoryTest {

	@Mock
	private CityWeatherConditionRepository cityWeatherConditionRepository;

	@Mock
	private View mockView;

	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void crudTest() {
		CityWeatherCondition cityWeatherCondition = new CityWeatherCondition();
		cityWeatherCondition.setCity("London");
		cityWeatherCondition.setCountry("GB");
		Weather weather = new Weather();
		weather.setMain("cloud");
		weather.setDescription("cloud");
		cityWeatherCondition.getWeather().add(weather);
		when(cityWeatherConditionRepository.save(cityWeatherCondition)).thenReturn(cityWeatherCondition);
		CityWeatherCondition objectSaved = cityWeatherConditionRepository.save(cityWeatherCondition);
		when(cityWeatherConditionRepository.findOne(objectSaved.getId())).thenReturn(objectSaved);
	}

}
