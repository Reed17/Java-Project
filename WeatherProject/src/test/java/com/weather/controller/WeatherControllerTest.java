package com.weather.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.View;

import com.weather.model.CityWeatherCondition;
import com.weather.service.CityTemperatureService;

public class WeatherControllerTest {

	@InjectMocks
	WeatherController weatherController;

	@Mock
	CityTemperatureService cityTemperatureService;

	@Mock
	View mockView;

	MockMvc mockMvc;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(weatherController).setSingleView(mockView).build();
	}

	@Test
	public void indexTest() throws Exception {
		mockMvc.perform(get("/")).andExpect(status().isOk());
	}

	@Test
	public void testUserController() throws Exception {
		CityWeatherCondition cityWeatherCondition = new CityWeatherCondition();
		when(cityTemperatureService.connectWeatherService("london")).thenReturn(cityWeatherCondition);

		mockMvc.perform(get("/weather/{city}", "london")).andExpect(status().isOk());
	}

}
