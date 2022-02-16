package com.voytenko.repository;

import com.voytenko.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository extends JpaRepository<Weather, Integer> {
}
