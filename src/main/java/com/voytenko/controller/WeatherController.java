package com.voytenko.controller;


import com.voytenko.model.User;
import com.voytenko.model.Weather;
import com.voytenko.repository.UserRepository;
import com.voytenko.repository.WeatherRepository;
import com.voytenko.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class WeatherController {


    private WeatherService service;
    private WeatherRepository weatherRepository;
    private UserRepository userRepository;

    @Autowired
    public WeatherController(WeatherService service, WeatherRepository weatherRepository, UserRepository userRepository) {
        this.service = service;
        this.weatherRepository = weatherRepository;
        this.userRepository = userRepository;
    }


    @GetMapping("/weather")
    public String getWeather(@RequestParam Optional<String> city, @RequestParam Optional<String> email) throws IOException {

        StringBuilder json = new StringBuilder(service.searchWeatherJSON("https://api.openweathermap.org/data/2.5/weather?q="
                + city.orElse("Kazan") + "&appid=50da205a9c76cfaf41a554bc57768910"));

        if (email.isPresent()) {
            User user = userRepository.getByEmail(email.get());
            if (user == null) {
                return json + " null";
            }
            Map<String, Object> weatherParse = service.parseGson(json);

            weatherRepository.save(new Weather(user, city.orElse("Kazan"), weatherParse.get("main humidity").toString(), LocalDateTime.now().toString()));
        }

        return json + " " + email.orElse(null);
    }

    @GetMapping("/allCities")
    public List<Weather> getAllWeatherSearch() throws IOException {
        return weatherRepository.findAll();
    }

}
