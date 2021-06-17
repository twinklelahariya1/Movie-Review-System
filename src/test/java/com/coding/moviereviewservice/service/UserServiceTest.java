package com.coding.moviereviewservice.service;

import com.coding.moviereviewservice.enums.Role;
import com.coding.moviereviewservice.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Mock
    Map<Long, User> users = new HashMap<>();

    User user = new User(1L, "Twinkle", Role.VIEWER);

    @Test
    public void createUserTest() {
        Assertions.assertEquals(userService.createUser(user).getName(), user.getName());
    }

    @Test
    public void getUserById() {

        users.put(user.getId(), user);
        Assertions.assertEquals(userService.getUserById(1).getName(), "Twinkle");
    }


}
