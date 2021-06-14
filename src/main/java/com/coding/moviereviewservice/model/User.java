package com.coding.moviereviewservice.model;

import com.coding.moviereviewservice.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class User {

    private final int id;

    private final String name;

    private Role role;

    private List<Movie> movies;

    public User(int id, String name, Role role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;

    }
}
