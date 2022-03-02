package com.voytenko.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.voytenko.model.Resource;
import com.voytenko.model.User;
import com.voytenko.model.Weather;
import com.voytenko.repository.ResourceRepository;
import com.voytenko.repository.UserRepository;
import com.voytenko.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class WeatherServiceImpl implements WeatherService {

    private UserRepository userRepository;
    private WeatherRepository weatherRepository;
    private ResourceRepository resourceRepository;

    @Autowired
    public WeatherServiceImpl(UserRepository userRepository, WeatherRepository weatherRepository, ResourceRepository resourceRepository) {
        this.userRepository = userRepository;
        this.weatherRepository = weatherRepository;
        this.resourceRepository = resourceRepository;
    }

    @Override
    public String searchWeatherJSON(String url) {
        try {
            URL finalUrl = new URL(url);
            HttpURLConnection connectionGet = (HttpURLConnection) finalUrl.openConnection();
            connectionGet.setRequestMethod("GET");
            StringBuilder requestResult;

            try (BufferedReader reader =
                         new BufferedReader(new InputStreamReader(connectionGet.getInputStream()))) {
                requestResult = new StringBuilder();

                String input;
                while ((input = reader.readLine()) != null) {
                    requestResult.append(input);
                }
            }
            connectionGet.disconnect();
            return requestResult.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String, Object> parseGson(StringBuilder jsonString) {
        ObjectMapper mapper = new ObjectMapper();

        Map map = null;
        try {
            map = mapper.readValue(jsonString.toString(), Map.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Map<String, Object> mapRes = new HashMap<>();

        for (Object string : map.keySet()) {
            if (map.get(string).getClass() == LinkedHashMap.class || map.get(string).getClass() == ArrayList.class) {
                HashMap hashMap = null;

                if (map.get(string).getClass() == LinkedHashMap.class) {
                    hashMap = (HashMap) map.get(string);
                } else if (map.get(string).getClass() == ArrayList.class) {
                    ArrayList arrayList = (ArrayList) map.get(string);
                    hashMap = (HashMap) arrayList.get(0);
                }

                for (Object stringHashMap : hashMap.keySet()) {
                    mapRes.put( string + " " +  stringHashMap, hashMap.get(stringHashMap));
                }

            } else {
                mapRes.put((String) string, map.get(string));
            }
        }
        return mapRes;
    }

    @Override
    public String getWeather(Optional<String> city, Optional<String> email) {
        StringBuilder json = new StringBuilder(searchWeatherJSON("https://api.openweathermap.org/data/2.5/weather?q="
                + city.orElse("Kazan") + "&appid=50da205a9c76cfaf41a554bc57768910"));

        if (email.isPresent()) {
            User user = userRepository.getByEmail(email.get());
            if (user == null) {
                return json + " null";
            }
            Map<String, Object> weatherParse = parseGson(json);


            Weather weather = new Weather(city.orElse("Kazan"), weatherParse.get("main humidity").toString(), LocalDateTime.now().toString());
            Resource resource = new Resource(user, city.orElse("Kazan"), weather);

            resourceRepository.save(resource);

        }

        return json + " " + email.orElse(null);
    }

    @Override
    public List<Weather> findAll() {
        return weatherRepository.findAll();
    }

    @Override
    public List<Weather> findAllByCity(String city) {
        return weatherRepository.findAllByCity(city);
    }

}
