package com.coding.moviereviewservice.service;

import com.coding.moviereviewservice.enums.Role;
import com.coding.moviereviewservice.model.APIResponse;
import com.coding.moviereviewservice.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void createUserTest() {
        User user = new User(1, "Twinkle", Role.VIEWER);
//        Assertions.assertEquals(userService.createUser(user).getData(),user);

    }

    @Test
    public void getUserTest() {
//        User user = new User(1, "Twinkle", Role.VIEWER);
//        Assertions.assertEquals(userService.getUsers().getStatus(), APIResponse.success().getStatus());

    }
}
