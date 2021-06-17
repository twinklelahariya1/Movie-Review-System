package com.coding.moviereviewservice.repository;

import com.coding.moviereviewservice.model.User;

import java.util.*;

public class UserRepository {

    Map<Integer, User> users = new HashMap<>();

    public void addData(User user) {
        users.put(user.getId(), user);
    }

    public Optional<User> getData(int id) {
        return Optional.ofNullable(users.get(id));
    }

    public List<User> getAllData() {
        return new ArrayList<>(users.values());
    }


}
