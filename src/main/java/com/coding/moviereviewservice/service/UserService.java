package com.coding.moviereviewservice.service;

import com.coding.moviereviewservice.enums.Role;
import com.coding.moviereviewservice.model.User;

import java.util.List;

public interface UserService {

    User createUser(User user);

    User getUserById(int id);

    List<User> getUsers();

    Role getUserRole(int userId);
}
