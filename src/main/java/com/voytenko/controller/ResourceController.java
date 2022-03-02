package com.voytenko.controller;

import com.voytenko.dto.ResourceDto;
import com.voytenko.repository.ResourceRepository;
import com.voytenko.services.ResourceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ResourceController {

    private final ResourceService service;

    public ResourceController(ResourceService service) {
        this.service = service;
    }

    @GetMapping("/resource/byUser/{id}")
    public List<ResourceDto> get(@PathVariable Integer id) {
        return ResourceDto.from(service.findAllByUserId(id));
    }

    @GetMapping("/resource/byCity/{city}")
    public List<ResourceDto> get(@PathVariable String city) {
        return ResourceDto.from(service.findAllByCity(city));
    }
}
