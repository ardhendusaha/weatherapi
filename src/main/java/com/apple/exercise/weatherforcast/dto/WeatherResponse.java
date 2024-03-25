package com.apple.exercise.weatherforcast.dto;

public class WeatherResponse {
	private WeatherDTO weatherDTO;
	private String pulledFrom;
	private String status;
	private String message;
	public WeatherDTO getWeatherDTO() {
		return weatherDTO;
	}
	public void setWeatherDTO(WeatherDTO weatherDTO) {
		this.weatherDTO = weatherDTO;
	}
	public String getPulledFrom() {
		return pulledFrom;
	}
	public void setPulledFrom(String pulledFrom) {
		this.pulledFrom = pulledFrom;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
