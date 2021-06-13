package com.coding.moviereviewservice.service;

import com.coding.moviereviewservice.model.APIResponse;
import com.coding.moviereviewservice.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    APIResponse createUser(User user);

    APIResponse getUserById(int id);

    APIResponse getUsers();
}
