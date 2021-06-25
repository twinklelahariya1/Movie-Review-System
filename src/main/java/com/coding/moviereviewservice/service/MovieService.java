package com.coding.moviereviewservice.service;

import com.coding.moviereviewservice.enums.Genre;
import com.coding.moviereviewservice.model.Movie;
import com.coding.moviereviewservice.model.UserReview;

import java.util.List;

public interface MovieService {
    Movie createMovie(Movie movie);

    Movie getMovieById(Long id);

    Movie reviewMovie(Long movieId, UserReview userReview);

    int getAverageMovieReview(Long movieId);

    List<Movie> getTopNCriticMovieReviewByGenre(Genre genre, Integer count);
}
