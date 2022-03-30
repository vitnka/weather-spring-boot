package com.voytenko.controller;

import com.voytenko.Application;
import com.voytenko.model.User;
import com.voytenko.repository.UserRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void init() {
        User user = new User();
        user.setEmail("m@mail.ru");
        user.setName("Ivan");
        user.setPassword("password");
        user.setVerificationCode("1000");
        userRepository.save(user);
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Ivan"));
    }

//    @Test
//    public void testVerify() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.
//                get("/verification?code=1000")).
//                andExpect(status().isOk()).
//                andExpect(content().string("verification_success"));
//    }

    @Test
    public void testGetById() throws Exception {
        User user = new User();
        user.setEmail("m@mail.ru");
        user.setName("Ivan");
        user.setPassword("password");
        user.setVerificationCode("1000");
        userRepository.save(user);
        mockMvc.perform(get("/user/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("name").value("Ivan"));
    }
}