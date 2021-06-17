package com.coding.moviereviewservice.service.impl;

import com.coding.moviereviewservice.enums.Role;
import com.coding.moviereviewservice.model.User;
import com.coding.moviereviewservice.repository.UserRepository;
import com.coding.moviereviewservice.service.UserService;
import com.coding.moviereviewservice.util.CustomException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        userRepository.addData(user);
        return user;
    }

    public User getUserById(Long id) {
        return userRepository.getData(id)
                .orElseThrow(() -> new CustomException().dataNotFound("User Not Found with Id + " + id));
    }

    @Override
    public List<User> getUsers() {
        return userRepository.getAllData();
    }

    @Override
    public Role getUserRole(Long userId) {
        return getRole(userId);
    }

    private Role getRole(Long userId) {
        User user = userRepository.getData(userId)
                .orElseThrow(() -> new CustomException().dataNotFound("User Not Found with Id + " + userId));
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