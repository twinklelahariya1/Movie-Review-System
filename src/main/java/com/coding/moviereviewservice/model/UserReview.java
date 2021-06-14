package com.coding.moviereviewservice.model;

import com.coding.moviereviewservice.enums.Rating;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserReview {

    private int userId;

    private Rating rating;

}
