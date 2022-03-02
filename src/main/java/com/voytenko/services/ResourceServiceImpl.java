package com.voytenko.services;

import com.voytenko.model.Resource;
import com.voytenko.repository.ResourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ResourceServiceImpl implements ResourceService {

    private ResourceRepository repository;

    @Autowired
    public ResourceServiceImpl(ResourceRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Resource> findAllByUserId(Integer id) {
        try {

            return repository.findAllByUserId(id);

        } catch (IllegalArgumentException e){
            System.err.print("This resource doesn't exist");
            return null;
        }
    }

    @Override
    public List<Resource> findAllByCity(String city) {
        try {

            return repository.findAllByCity(city);

        } catch (IllegalArgumentException e){
            System.err.print("This resource doesn't exist");
            return null;
        }
    }
}
