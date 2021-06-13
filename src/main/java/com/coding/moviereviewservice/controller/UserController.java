package com.coding.moviereviewservice.controller;

import com.coding.moviereviewservice.model.APIResponse;
import com.coding.moviereviewservice.model.User;
import com.coding.moviereviewservice.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/vi/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "")
    public APIResponse createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping(path = "/id/{id}")
    public APIResponse getUser(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @GetMapping(path = "s")
    public APIResponse getUsers() {
        return userService.getUsers();
    }
}
