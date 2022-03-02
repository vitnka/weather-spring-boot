package com.voytenko.model;

import lombok.*;


import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "resource_id")
    private Resource resource;

    private String city;
    private String humidity;
    private String time;


    public Weather(String city, String humidity, String time) {
        this.city = city;
        this.humidity = humidity;
        this.time = time;
    }

}
