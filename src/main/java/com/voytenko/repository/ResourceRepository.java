package com.voytenko.repository;


import com.voytenko.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResourceRepository extends JpaRepository<Resource, Integer> {

    List<Resource> findAllByUserId(Integer id);

    List<Resource> findAllByCity(String city);
}
