package com.coding.moviereviewservice.controller;

import com.coding.moviereviewservice.enums.Role;
import com.coding.moviereviewservice.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {


    private final UserController userController;
    private final LocalServerPort localServerPort;
    private final TestRestTemplate testRestTemplate;

    public UserControllerTest(UserController userController, LocalServerPort localServerPort, TestRestTemplate testRestTemplate) {
        this.userController = userController;
        this.localServerPort = localServerPort;
        this.testRestTemplate = testRestTemplate;
    }

    @Test
    public void createUser() {

//        assertThat(this.testRestTemplate.postForEntity("http://localhost:" + localServerPort + "/api/vi/user/createUser"))
//        userController.createUser(new User(1, "Twinkle", null, Role.VIEWER));
    }
}
