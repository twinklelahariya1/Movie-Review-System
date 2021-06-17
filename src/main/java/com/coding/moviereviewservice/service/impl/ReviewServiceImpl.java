package com.coding.moviereviewservice.service.impl;

import com.coding.moviereviewservice.enums.Role;
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
    public Movie reviewMovie(UserReview userReview, Long movieId) {

        if (movieService.canReviewMovie(movieId)) {
            movieService.reviewMovie(movieId, userReview);
        }

        return null;
    }

    @Override
    public Movie getMovieReview(Long movieId) {
        Movie movie = movieService.getMovieById(movieId);
        int review = computeReview(movie);
        return null;
    }

    private int computeReview(Movie movie) {
        return movie.getUserReview().stream().map(this::computeRatingForMovie)
                .mapToInt(e -> e)
                .sum();

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
