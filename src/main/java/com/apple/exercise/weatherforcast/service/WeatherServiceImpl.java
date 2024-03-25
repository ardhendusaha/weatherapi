package com.apple.exercise.weatherforcast.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apple.exercise.weatherforcast.dto.WeatherDTO;
import com.apple.exercise.weatherforcast.dto.WeatherResponse;
import com.apple.exercise.weatherforcast.model.WeatherDetail;
import com.apple.exercise.weatherforcast.repository.WeatherRepository;
import com.apple.exercise.weatherforcast.util.CacheStore;

@Service
public class WeatherServiceImpl implements WeatherService {
	
	@Autowired
	private WeatherRepository weatherRepository;
	
	@Autowired
	CacheStore<WeatherDTO> weatherCache;

	@Override
	public void saveWeather(WeatherDTO weather) {
		// TODO Auto-generated method stub
		WeatherDetail weatherDetail = new WeatherDetail();
		weatherDetail.setZipcode(weather.getZipcode());
		weatherDetail.setTemperature(weather.getTemperature());
		weatherDetail.setUpdatedTs(new Date(System.currentTimeMillis()));
		try {
			weatherRepository.save(weatherDetail);
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Failed to save weather into DB. "+ e.getMessage() );
		}
		
	}

	@Override
	public WeatherResponse getWeather(int zipCode) {
		WeatherResponse response = new WeatherResponse();
		 //Fetch weather from cache
		WeatherDTO cachedWeather = weatherCache.get(zipCode);
        if(cachedWeather != null) {
            System.out.println("zipCode record found in cache : " + zipCode);
            response.setWeatherDTO(cachedWeather); 
            response.setPulledFrom("CACHE");
            response.setStatus("SUCCESS");
            return response;
        }
        
        //Fetch weather from backend service
		WeatherDTO weatherDTO = new WeatherDTO();
		try {
			WeatherDetail weatherDetail = weatherRepository.findByZipcode(zipCode);
			
			if(weatherDetail == null) {
				response.setStatus("SUCCESS");
	        	response.setMessage("Not Found any weather information for zipcode - "+zipCode);
	        	return response;
			}
			weatherDTO.setTemperature(weatherDetail.getTemperature());
			weatherDTO.setZipcode(weatherDetail.getZipcode());
			weatherDTO.setHighTemperature(weatherRepository.findMaxTempByZipcode(zipCode));
			weatherDTO.setLowTemperature(weatherRepository.findMinTempByZipcode(zipCode));
			
			response.setWeatherDTO(weatherDTO); 
            response.setPulledFrom("DB");
            response.setStatus("SUCCESS");
            
            //Store weather record in Cache
            weatherCache.add(zipCode, weatherDTO);
		}catch (Exception e) {
			// TODO: handle exception
			response.setStatus("FAILED");
        	response.setMessage("Failed to retrieve weather from DB - "+zipCode);
			System.out.println("Failed to retrieve weather from DB. "+ e.getMessage() );
		}
		
		return response;
	}
	
}
