package com.coding.moviereviewservice.model;

import com.coding.moviereviewservice.enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor
public class Movie {

    private final Long id;

    private final String name;

    private final Date releaseDate;

    private final Genre genre;

    private List<UserReview> userReview;

    public void setUserReview(List<UserReview> userReview) {
        this.userReview = userReview;
    }
}
