package com.voytenko.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class WeatherServiceImpl implements WeatherService {

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

    @Override
    public Map<String, Object> parseGson(StringBuilder jsonString) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        Map map = mapper.readValue(jsonString.toString(), Map.class);
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

}
