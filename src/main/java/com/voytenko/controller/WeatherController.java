package com.voytenko.controller;


import com.voytenko.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class WeatherController {


    private final WeatherService service;

    @Autowired
    public WeatherController(WeatherService service) {
        this.service = service;
    }


    @GetMapping("/weather")
    public String getWeather(@RequestParam Optional<String> city) {
        return service.searchWeatherJSON("https://api.openweathermap.org/data/2.5/weather?q="
                + city.orElse("Kazan") + "&appid=50da205a9c76cfaf41a554bc57768910");
    }
}
