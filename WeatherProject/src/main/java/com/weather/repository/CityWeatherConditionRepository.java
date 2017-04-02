package com.weather.repository;

import org.springframework.data.repository.CrudRepository;

import com.weather.model.CityWeatherCondition;

public interface CityWeatherConditionRepository extends CrudRepository<CityWeatherCondition, Long>{

	
}
