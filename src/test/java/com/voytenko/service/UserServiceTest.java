package com.voytenko.service;

import com.voytenko.config.MailConfig;
import com.voytenko.repository.UserRepository;
import com.voytenko.services.UserService;
import com.voytenko.services.UserServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class UserServiceTest {

    @TestConfiguration
    static class UserServiceTestContextConfiguration {

        @MockBean
        private UserRepository userRepository;

        @MockBean
        private BCryptPasswordEncoder encoder;

        @MockBean
        private JavaMailSender javaMailSender;

        @MockBean
        private MailConfig mailConfig;


        @Bean
        public UserService userService() {
            return new UserServiceImpl(userRepository, encoder, javaMailSender, mailConfig);
        }
    }

    @Autowired
    private UserService userService;

    @Test
    public void testGetAll() {
        Assert.assertTrue(userService.getAll().isEmpty());
    }
}
