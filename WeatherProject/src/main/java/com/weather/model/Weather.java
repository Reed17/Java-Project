package com.weather.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Weather {

	@Id@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String main;
	private String description;
	@ManyToOne
	private CityWeatherCondition cityWeatherCondition; 
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMain() {
		return main;
	}
	public void setMain(String main) {
		this.main = main;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public CityWeatherCondition getCityWeatherCondition() {
		return cityWeatherCondition;
	}
	public void setCityWeatherCondition(CityWeatherCondition cityWeatherCondition) {
		this.cityWeatherCondition = cityWeatherCondition;
	}
	
}
