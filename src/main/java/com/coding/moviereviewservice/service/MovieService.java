package com.coding.moviereviewservice.service;

import com.coding.moviereviewservice.model.Movie;
import com.coding.moviereviewservice.model.UserReview;

public interface MovieService {
    Movie createMovie(Movie movie);

    Movie getMovieById(Long id);

    boolean canReviewMovie(Long movieId);

    void reviewMovie(Long movieId, UserReview userReview);

    int getMovieReview(Long movieId);
}
