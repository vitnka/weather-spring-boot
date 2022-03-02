package com.voytenko.services;

import com.voytenko.model.Weather;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface WeatherService {

    String searchWeatherJSON(String url);

    String getWeather(Optional<String> city, Optional<String> email);

    List<Weather> findAll();

    List<Weather> findAllByCity(String city);
}
