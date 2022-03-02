package com.voytenko.dto;

import com.voytenko.model.Resource;
import com.voytenko.model.User;
import com.voytenko.model.Weather;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;


@Builder
@Data
public class ResourceDto {

    private Integer id;
    private User user;
    private String city;
    private Weather weather;

    public static ResourceDto from(Resource resource){
        return ResourceDto.builder()
                .id(resource.getId())
                .city(resource.getCity())
                .user(resource.getUser())
                .weather(resource.getWeather())
                .build();
    }

    public static List<ResourceDto> from(List<Resource> resources){
        return resources.stream().map(ResourceDto::from).collect(Collectors.toList());
    }

}
