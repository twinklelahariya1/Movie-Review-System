package com.coding.moviereviewservice.service.impl;

import com.coding.moviereviewservice.model.APIResponse;
import com.coding.moviereviewservice.model.User;
import com.coding.moviereviewservice.service.UserService;
import com.coding.moviereviewservice.util.CustomException;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    List<User> users = new ArrayList<>();

    public APIResponse createUser(User user) {
        users.add(user);
        return APIResponse.success();
    }

    public APIResponse getUserById(int id) {
        User user = users.stream().filter(u -> id == u.getId()).findAny()
                .orElseThrow(() -> new CustomException().dataNotFound("User Not Found with Id + " + id));
        return APIResponse.success(user);
    }

    @Override
    public APIResponse getUsers() {
        return APIResponse.success(users);
    }
}
