package com.coding.moviereviewservice.service.impl;

import com.coding.moviereviewservice.model.APIResponse;
import com.coding.moviereviewservice.model.User;
import com.coding.moviereviewservice.service.UserService;
import com.coding.moviereviewservice.util.CustomException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    List<User> users = new ArrayList<>();

    public User createUser(User user) {
        users.add(user);
        return user;
    }

    public User getUserById(int id) {
        return users.stream().filter(u -> id == u.getId()).findAny()
                .orElseThrow(() -> new CustomException().dataNotFound("User Not Found with Id + " + id));
    }

    @Override
    public List<User> getUsers() {
        return users;
    }
}