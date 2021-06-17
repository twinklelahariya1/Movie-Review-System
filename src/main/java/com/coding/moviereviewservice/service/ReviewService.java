package com.coding.moviereviewservice.service;

import com.coding.moviereviewservice.model.Movie;
import com.coding.moviereviewservice.model.UserReview;

import java.util.List;

public interface ReviewService {

    int computeReview(Movie movie);

    int getMovieReview(List<UserReview> userReview);
}
