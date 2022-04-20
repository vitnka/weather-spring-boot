package com.voytenko.controller;


import com.voytenko.aspect.Loggable;
import com.voytenko.dto.WeatherDto;
import com.voytenko.model.Weather;
import com.voytenko.services.WeatherService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
public class WeatherController {


    private WeatherService service;

    @Autowired
    public WeatherController(WeatherService service) {
        this.service = service;
    }

    @ApiOperation(value = "Отображение результа поиска погоды в определенном городе авторизированного поьзователя", notes = "Возвращает json-объект")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The weather was not found")
    })
    @GetMapping("/weather")
    @Loggable
    public String getWeather(@RequestParam Optional<String> city, HttpServletRequest request) throws IOException {
        Optional<String> email = Optional.ofNullable(request.getUserPrincipal().getName());
        return service.getWeather(city, email);
    }

    @ApiOperation(value = "Отображение результа поиска погоды во всех городах", notes = "Возвращает список с объектами класса 'Weather' ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The weather was not found")
    })
    @GetMapping("/allCities")
    public List<Weather> getAllWeatherSearch() throws IOException {
        return service.findAll();
    }

    @ApiOperation(value = "Отображение результа поиска погоды в определенном городе", notes = "Возвращает json-объект")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The weather was not found")
    })
    @Loggable
    @GetMapping("/weather/{city}")
    public List<WeatherDto> get(@PathVariable String city) {
        return WeatherDto.from(service.findAllByCity(city));
    }

}
