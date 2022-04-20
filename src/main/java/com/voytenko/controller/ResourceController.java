package com.voytenko.controller;

import com.voytenko.dto.ResourceDto;
import com.voytenko.services.ResourceService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value = "Получение ресурса по индентификатору пользоваетеля ", notes = "Возвращает список ресурсов одного пользователя")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The resource was not found")
    })
    @GetMapping("/resource/byUser/{id}")
    public List<ResourceDto> get(@PathVariable Integer id) {
        return ResourceDto.from(service.findAllByUserId(id));
    }

    @ApiOperation(value = "Получение ресурса в пределах одного города", notes = "Возвращает список ресурсов одного города")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The resource was not found")
    })
    @GetMapping("/resource/byCity/{city}")
    public List<ResourceDto> get(@PathVariable String city) {
        return ResourceDto.from(service.findAllByCity(city));
    }
}
