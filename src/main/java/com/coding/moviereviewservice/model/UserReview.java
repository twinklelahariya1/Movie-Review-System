package com.coding.moviereviewservice.model;

import com.coding.moviereviewservice.enums.Rating;
import com.coding.moviereviewservice.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserReview {

    private final Long userId;

    @JsonIgnore
    private Role role;

    private final Rating rating;

    public UserReview(Long userId, Rating rating) {
        this.userId = userId;
        this.rating = rating;
    }
}
