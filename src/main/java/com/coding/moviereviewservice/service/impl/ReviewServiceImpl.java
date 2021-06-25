package com.coding.moviereviewservice.service.impl;

import com.coding.moviereviewservice.model.Movie;
import com.coding.moviereviewservice.model.UserReview;
import com.coding.moviereviewservice.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Override
    public int computeAverageReview(Movie movie) {
        List<UserReview> userReview = movie.getUserReview();
        int sum = getMovieReview(userReview);
        return sum / userReview.size();

    }

    @Override
    public int getMovieReview(List<UserReview> userReview) {
        return userReview.stream().map(this::computeRatingForMovie)
                .mapToInt(e -> e)
                .sum();
    }

    private Integer computeRatingForMovie(UserReview userReview) {
        return userReview.getRole().compute(userReview.getRating().getValue());
    }
}
