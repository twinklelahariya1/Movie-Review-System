package com.coding.moviereviewservice.service.impl;

import com.coding.moviereviewservice.enums.Role;
import com.coding.moviereviewservice.model.User;
import com.coding.moviereviewservice.service.UserService;
import com.coding.moviereviewservice.util.CustomException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    Map<Integer, User> users = new HashMap<>();

    public User createUser(User user) {
        users.put(user.getId(), user);
        return user;
    }

    public User getUserById(int id) {
        return users.values().stream().filter(u -> id == u.getId()).findAny()
                .orElseThrow(() -> new CustomException().dataNotFound("User Not Found with Id + " + id));
    }

    @Override
    public Map<Integer, User> getUsers() {
        return users;
    }

    @Override
    public Role getUserRole(int userId) {
        return users.get(userId).getRole();
        //update role;
    }
}