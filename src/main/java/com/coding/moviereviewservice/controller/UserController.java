package com.coding.moviereviewservice.controller;

import com.coding.moviereviewservice.model.APIResponse;
import com.coding.moviereviewservice.model.User;
import com.coding.moviereviewservice.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/vi/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/")
    public APIResponse createUser(@RequestBody User user) {
        User response = userService.createUser(user);
        return APIResponse.success(response);
    }

    @GetMapping(path = "/{id}")
    public APIResponse getUser(@PathVariable int id) {
        User user = userService.getUserById(id);
        return APIResponse.success(user);
    }

    @GetMapping(path = "/")
    public APIResponse getUsers() {
        List<User> users = userService.getUsers();
        return APIResponse.success(users);
    }
}
