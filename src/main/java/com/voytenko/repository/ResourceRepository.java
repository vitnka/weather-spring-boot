package com.voytenko.repository;


import com.voytenko.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ResourceRepository extends JpaRepository<Resource, Integer> {

    List<Resource> findAllByUserId(Integer id);

    List<Resource> findAllByCity(String city);
}
