package com.voytenko.repository;

import com.voytenko.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface WeatherRepository extends JpaRepository<Weather, Integer> {
    List<Weather> findAllByCity(String city);
}
