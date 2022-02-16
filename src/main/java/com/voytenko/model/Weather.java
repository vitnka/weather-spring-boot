package com.voytenko.model;

import javax.persistence.*;

@Entity
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private User user;

    private String city;
    private String humidity;
    private String time;

    public Weather(Integer id, User user, String city, String humidity, String time) {
        this.id = id;
        this.user = user;
        this.city = city;
        this.humidity = humidity;
        this.time = time;
    }

    public Weather(User user, String city, String humidity, String time) {
        this.user = user;
        this.city = city;
        this.humidity = humidity;
        this.time = time;
    }

    public Weather() {
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
