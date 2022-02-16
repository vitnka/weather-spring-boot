package com.voytenko.dto;

import com.voytenko.model.User;
import com.voytenko.model.Weather;

public class WeatherDto {
    private Integer id;
    private User user;

    private String city;
    private String humidity;
    private String time;

    public WeatherDto(Integer id, User user, String city, String humidity, String time) {
        this.id = id;
        this.user = user;
        this.city = city;
        this.humidity = humidity;
        this.time = time;
    }

    public static WeatherDto fromModel(Weather weather) {
        return new WeatherDto(weather.getId(), weather.getUser(), weather.getCity(), weather.getHumidity(), weather.getTime());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
