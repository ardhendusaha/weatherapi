package com.apple.exercise.weatherforcast.dto;

public class WeatherDTO {

	private int zipcode;
	private float temperature;
	private float lowTemperature;
	private float highTemperature;
	public int getZipcode() {
		return zipcode;
	}
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
	public float getTemperature() {
		return temperature;
	}
	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}
	public float getLowTemperature() {
		return lowTemperature;
	}
	public void setLowTemperature(float lowTemperature) {
		this.lowTemperature = lowTemperature;
	}
	public float getHighTemperature() {
		return highTemperature;
	}
	public void setHighTemperature(float highTemperature) {
		this.highTemperature = highTemperature;
	}
	
	
}
