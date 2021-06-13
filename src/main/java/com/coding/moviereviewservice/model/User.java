package com.coding.moviereviewservice.model;

import com.coding.moviereviewservice.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {

    private final int id;

    private final String name;

    private Role role;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
