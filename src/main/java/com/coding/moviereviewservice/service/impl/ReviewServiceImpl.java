package com.coding.moviereviewservice.service.impl;

import com.coding.moviereviewservice.enums.Role;
import com.coding.moviereviewservice.model.Movie;
import com.coding.moviereviewservice.model.UserReview;
import com.coding.moviereviewservice.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Override
    public int computeReview(Movie movie) {
        List<UserReview> userReview = movie.getUserReview();
        int sum = userReview.stream().map(this::computeRatingForMovie)
                .mapToInt(e -> e)
                .sum();
        return sum / userReview.size();

    }

    private Integer computeRatingForMovie(UserReview userReview) {
        return userReview.getRole().rating.apply(userReview.getRole(), userReview.getRating().getRating());
    }


    public static Integer computeViewerRating(Role role, Integer rating) {
        return rating;
    }

    public static Integer computeCriticRating(Role role, Integer rating) {
        return rating * 3;
    }

    public static Integer computeExpertRating(Role role, Integer rating) {
        return rating * 6;
    }

    public static Integer computeAdminRating(Role role, Integer rating) {
        return rating * 9;
    }
}
