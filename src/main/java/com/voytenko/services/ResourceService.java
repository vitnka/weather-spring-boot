package com.voytenko.services;

import com.voytenko.model.Resource;

import java.util.List;

public interface ResourceService {

    List<Resource> findAllByUserId(Integer id);
    List<Resource> findAllByCity(String city);
}
