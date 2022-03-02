package com.voytenko.dto;

import com.voytenko.model.Resource;
import com.voytenko.model.User;
import com.voytenko.model.Weather;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class WeatherDto {
    private Integer id;

    private String city;
    private String humidity;
    private String time;

    public WeatherDto(Integer id,  String city, String humidity, String time) {
        this.id = id;
        this.city = city;
        this.humidity = humidity;
        this.time = time;
    }


    public static List<WeatherDto> from(List<Weather> weathers) {
        return weathers.stream().map(WeatherDto::from).collect(Collectors.toList());
    }

    public static WeatherDto from( Weather weather){
        return WeatherDto.builder()
                .city(weather.getCity())
                .id(weather.getId())
                .humidity(weather.getHumidity())
                .time(weather.getTime())
                .build();
    }
}
