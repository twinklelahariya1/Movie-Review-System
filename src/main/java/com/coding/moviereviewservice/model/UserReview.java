package com.coding.moviereviewservice.model;

import com.coding.moviereviewservice.enums.Rating;
import com.coding.moviereviewservice.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserReview {

    private Long userId;

    @JsonIgnore
    private Role role;

    private Rating rating;

}
