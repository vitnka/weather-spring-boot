package com.voytenko.services;

import com.voytenko.dto.CreateUserDto;
import com.voytenko.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto findById (Integer id);
    List<UserDto> findAll();
    UserDto save(CreateUserDto createUserDto);
}
