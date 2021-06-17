package com.coding.moviereviewservice.service.impl;

import com.coding.moviereviewservice.model.Movie;
import com.coding.moviereviewservice.model.UserReview;
import com.coding.moviereviewservice.service.MovieService;
import com.coding.moviereviewservice.service.ReviewService;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final MovieService movieService;

    public ReviewServiceImpl(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public Movie getMovieReview(Long movieId) {
        return null;
    }

    @Override
    public Movie reviewMovie(UserReview userReview, Long movieId) {

        if(movieService.canReviewMovie(movieId)){
            movieService.reviewMovie(movieId,userReview);
        }

        return null;
    }
}
