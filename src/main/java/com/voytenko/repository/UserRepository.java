package com.voytenko.repository;

import com.voytenko.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User getByEmail(String email);
}
