package com.coding.moviereviewservice.service;

import com.coding.moviereviewservice.enums.Role;
import com.coding.moviereviewservice.model.User;

import java.util.Map;

public interface UserService {

    User createUser(User user);

    User getUserById(int id);

    Map<Integer, User> getUsers();

    Role getUserRole(int userId);
}
