package com.coding.moviereviewservice.service;

import com.coding.moviereviewservice.enums.Role;
import com.coding.moviereviewservice.model.User;
import com.coding.moviereviewservice.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static java.util.Optional.ofNullable;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    User user = new User(1L, "Twinkle", Role.VIEWER);

    @Test
    public void createUserTest() {
        Assertions.assertEquals(userService.createUser(user).getName(), user.getName());
    }

    @Test
    public void getUserById() {

        Mockito.when(userRepository.getData(Mockito.any())).thenReturn(ofNullable(user));
        Assertions.assertEquals(userService.getUserById(1L).getName(), "Twinkle");

    }


}
