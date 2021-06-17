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
        return getRole(userId);
    }

    private Role getRole(int userId) {
        User user = users.get(userId);
        long count = user.getMovies().size();
        if (count > 3)
            user.setRole(Role.CRITIC);
        if (count > 10)
            user.setRole(Role.EXPERT);
        if (count > 50)
            user.setRole(Role.ADMIN);

        return user.getRole();
    }
}