package com.coding.moviereviewservice.service;

import com.coding.moviereviewservice.model.Movie;
import com.coding.moviereviewservice.model.UserReview;

public interface ReviewService {

    int getMovieReview(Long movieId);

    Movie reviewMovie(UserReview userReview, Long movieId);
}
