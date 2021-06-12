package com.coding.moviereviewservice.controller;

import com.coding.moviereviewservice.model.APIResponse;
import com.coding.moviereviewservice.model.User;
import com.coding.moviereviewservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/vi/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/createUser")
    public APIResponse createUser(@RequestBody User user) {

        return APIResponse.success();
    }
}
