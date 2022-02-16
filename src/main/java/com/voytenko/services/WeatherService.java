package com.voytenko.services;

import java.io.IOException;
import java.util.Map;

public interface WeatherService {

    String searchWeatherJSON(String url);
    Map<String, Object> parseGson(StringBuilder jsonString) throws IOException;
}
