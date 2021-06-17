package com.coding.moviereviewservice.service;

import com.coding.moviereviewservice.model.Movie;
import com.coding.moviereviewservice.model.UserReview;

public interface MovieService {
    Movie createMovie(Movie movie);

    Movie getUserById(Long id);

    boolean canReviewMovie(Long movieId);

    Movie reviewMovie(Long movieId, UserReview userReview);
}
