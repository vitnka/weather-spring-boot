package com.voytenko.services;

import com.voytenko.dto.CreateUserDto;
import com.voytenko.dto.UserDto;
import com.voytenko.helpers.PasswordHelper;
import com.voytenko.model.User;
import com.voytenko.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public UserDto findById(Integer id) {
        return UserDto.fromModel(userRepository.findById(id).get());
    }

    @Override
    public List<UserDto> findAll() {
        return UserDto.fromModel(userRepository.findAll());
    }

    @Override
    public UserDto save(CreateUserDto createUserDto) {
        return UserDto.fromModel(
                userRepository.save(
                User.builder()
                        .email(createUserDto.getEmail())
                        .name(createUserDto.getName())
                        .password(PasswordHelper.encrypt(createUserDto.getPassword()))
                        .build()));
    }
}
