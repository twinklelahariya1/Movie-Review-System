package com.coding.moviereviewservice.service.impl;

import com.coding.moviereviewservice.enums.Genre;
import com.coding.moviereviewservice.enums.Role;
import com.coding.moviereviewservice.model.Movie;
import com.coding.moviereviewservice.model.UserReview;
import com.coding.moviereviewservice.service.MovieService;
import com.coding.moviereviewservice.service.ReviewService;
import com.coding.moviereviewservice.service.UserService;
import com.coding.moviereviewservice.util.CustomException;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MovieServiceImpl implements MovieService {

    private final UserService userService;

    private final ReviewService reviewService;

    Map<Long, Movie> movies = new HashMap<>();

    public MovieServiceImpl(UserService userService, ReviewService reviewService) {
        this.userService = userService;
        this.reviewService = reviewService;
    }

    @Override
    public Movie createMovie(Movie movie) {

        movies.put(movie.getId(), movie);
        return movie;
    }

    @Override
    public Movie getMovieById(Long id) {
        return movies.get(id);
    }

    private boolean canReviewMovie(Long movieId) {
        Movie movie = movies.get(movieId);
        return Calendar.getInstance().getTime().after(movie.getReleaseDate());

    }

    @Override
    public Movie reviewMovie(Long movieId, UserReview userReview) {

        if (canReviewMovie(movieId)) {

            Role userRole = userService.getUserRole(userReview.getUserId());
            userReview.setRole(userRole);
            Movie movie = movies.get(movieId);
            List<UserReview> userReview1 = movie.getUserReview();
            userReview1.add(userReview);
            movie.setUserReview(userReview1);
            return movie;
        }
        throw new CustomException().serviceException();
    }

    @Override
    public int getAverageMovieReview(Long movieId) {
        return reviewService.computeReview(movies.get(movieId));
    }

    @Override
    public int getTopNCriticMovieReviewByGenre(Genre genre, Integer count) {
        return movies.values().stream().filter(movie -> genre.equals(movie.getGenre()))
                .limit(count)
                .map(reviewService::computeReview)
                .mapToInt(value -> value)
                .sum();
    }

}
