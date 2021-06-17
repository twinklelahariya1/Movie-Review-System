package com.coding.moviereviewservice.service.impl;

import com.coding.moviereviewservice.enums.Role;
import com.coding.moviereviewservice.model.Movie;
import com.coding.moviereviewservice.model.UserReview;
import com.coding.moviereviewservice.service.MovieService;
import com.coding.moviereviewservice.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MovieServiceImpl implements MovieService {

    private final UserService userService;

    Map<Long, Movie> movies = new HashMap<>();

    public MovieServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Movie createMovie(Movie movie) {

        movies.put(movie.getId(), movie);
        return movie;
    }

    @Override
    public Movie getUserById(Long id) {
        return movies.get(id);
    }

    @Override
    public boolean canReviewMovie(Long movieId) {
        Movie movie = movies.get(movieId);
        return Calendar.getInstance().getTime().after(movie.getReleaseDate());

    }

    @Override
    public Movie reviewMovie(Long movieId, UserReview userReview) {

        Role userRole = userService.getUserRole(userReview.getUserId());
        userReview.setRole(userRole);
        Movie movie = movies.get(movieId);
        List<UserReview> userReview1 = movie.getUserReview();
        userReview1.add(userReview);
        movie.setUserReview(userReview1);
        return movie; //refactor
    }
}
