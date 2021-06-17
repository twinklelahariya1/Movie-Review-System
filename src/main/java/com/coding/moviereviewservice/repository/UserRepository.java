package com.coding.moviereviewservice.repository;

import com.coding.moviereviewservice.model.User;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserRepository {

    Map<Long, User> users = new HashMap<>();

    public void addData(User user) {
        users.put(user.getId(), user);
    }

    public Optional<User> getData(Long id) {
        return Optional.ofNullable(users.get(id));
    }

    public List<User> getAllData() {
        return new ArrayList<>(users.values());
    }


}
