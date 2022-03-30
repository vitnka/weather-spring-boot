package com.voytenko.repository;


import com.voytenko.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testGetUserByEmail() {
        User user = new User();
        user.setEmail("test@mail.ru");
        user.setName("Ivan");
        user.setPassword("testTEST");

        testEntityManager.persistAndFlush(user);

        Optional<User> result = userRepository.getByEmail("test@mail.ru");
        Assert.assertTrue(result.isPresent());
    }
}
