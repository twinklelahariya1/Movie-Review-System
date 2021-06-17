package com.coding.moviereviewservice.service;

import com.coding.moviereviewservice.model.Movie;
import com.coding.moviereviewservice.model.UserReview;

public interface ReviewService {

    Movie getMovieReview(Long movieId);

    Movie reviewMovie(UserReview userReview, Long movieId);
}
