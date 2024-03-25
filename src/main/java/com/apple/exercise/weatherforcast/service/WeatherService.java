package com.apple.exercise.weatherforcast.service;

import com.apple.exercise.weatherforcast.dto.WeatherDTO;
import com.apple.exercise.weatherforcast.dto.WeatherResponse;

public interface WeatherService {

	public void saveWeather(WeatherDTO weather);
	public WeatherResponse getWeather(int zipCode);
}
