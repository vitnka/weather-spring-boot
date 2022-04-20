package com.voytenko.controller;

import com.voytenko.dto.CreateUserDto;
import com.voytenko.dto.UserDto;
import com.voytenko.services.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Получение списка всех пользователей", notes = "Возвращает список пользователей")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The user was not found")
    })
    @GetMapping("/users")
    @ResponseBody
    public Iterable<UserDto> getAll() {
        return userService.findAll();
    }

    @ApiOperation(value = "Получение пользователя по его идентификатору", notes = "Возвращает пользователя с определенным id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved", response = UserDto.class),
            @ApiResponse(code = 404, message = "Not found - The user was not found"),

    })
    @GetMapping("/user/{id}")
    @ResponseBody
    public UserDto getById(@PathVariable Integer id) {
        return userService.findById(id);
    }


    @ApiOperation(value = "Отображение страницы регистрации нового пользователя", notes = "Возвращает html-страницу")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The page was not found")
    })
    @PostMapping("/sign_up")
    public String signUp(@ModelAttribute(name = "user") CreateUserDto userDto, HttpServletRequest request) {
        String url = request.getRequestURL().toString().replace(request.getServletPath(), "");
        userService.signUp(userDto, url);
        return "sign_up_success";
    }

    @ApiOperation(value = "Отображение страницы результата верификации пользователя", notes = "Возвращает html-страницу")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The page was not found")
    })
    @GetMapping(("/verification"))
    public String verify(@Param("code") String code) {
        if (userService.verify(code)) {
            return "verification_success";
        } else {
            return "verification_failed";
        }
    }

}
