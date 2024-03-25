package com.apple.exercise.weatherforcast.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.apple.exercise.weatherforcast.model.WeatherDetail;

public interface WeatherRepository extends JpaRepository<WeatherDetail, Integer> {

	@Query(value = "SELECT * FROM tbl_weather WHERE zipcode =:zipCode order by updated_ts limit 0,1", nativeQuery = true)
	WeatherDetail findByZipcode(int zipCode);
	@Query(value = "SELECT max(temperature) as curTemp FROM tbl_weather WHERE zipcode =:zipCode", nativeQuery = true)
	float findMaxTempByZipcode(int zipCode);
	@Query(value = "SELECT min(temperature) as curTemp FROM tbl_weather WHERE zipcode =:zipCode", nativeQuery = true)
	float findMinTempByZipcode(int zipCode);

	
}
