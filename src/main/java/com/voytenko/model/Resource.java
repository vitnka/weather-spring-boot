package com.voytenko.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    private String city;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "weather_id")
    private Weather weather;

    public Resource(User user, String city, Weather weather) {
        this.user = user;
        this.city = city;
        this.weather = weather;
    }
}
