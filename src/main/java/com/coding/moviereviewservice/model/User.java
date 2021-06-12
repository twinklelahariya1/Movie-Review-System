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

    private List<Movie> reviewedMovies;

    private Role role;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setReviewedMovies(List<Movie> reviewedMovies) {
        this.reviewedMovies = reviewedMovies;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
