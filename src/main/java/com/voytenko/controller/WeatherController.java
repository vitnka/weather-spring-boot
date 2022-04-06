package com.voytenko.controller;


import com.voytenko.aspect.Loggable;
import com.voytenko.dto.ResourceDto;
import com.voytenko.dto.WeatherDto;
import com.voytenko.model.Resource;
import com.voytenko.model.User;
import com.voytenko.model.Weather;
import com.voytenko.repository.ResourceRepository;
import com.voytenko.repository.UserRepository;
import com.voytenko.repository.WeatherRepository;
import com.voytenko.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class WeatherController {


    private WeatherService service;

    @Autowired
    public WeatherController(WeatherService service) {
        this.service = service;
    }

//    @GetMapping("/weather")
//    public String getWeather(@RequestParam Optional<String> city, @RequestParam Optional<String> email) throws IOException {
//        return service.getWeather(city, email);
//    }

    @GetMapping("/weather")
    @Loggable
    public String getWeather(@RequestParam Optional<String> city, HttpServletRequest request)  throws IOException {
        Optional<String> email = Optional.ofNullable(request.getUserPrincipal().getName());
        return service.getWeather(city, email);
    }

    @GetMapping("/allCities")
    public List<Weather> getAllWeatherSearch() throws IOException {
        return service.findAll();
    }

    @Loggable
    @GetMapping("/weather/{city}")
    public List<WeatherDto> get(@PathVariable String city) {
        return WeatherDto.from(service.findAllByCity(city));
    }

}
