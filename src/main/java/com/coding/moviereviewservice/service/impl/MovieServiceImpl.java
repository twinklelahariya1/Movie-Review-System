package com.coding.moviereviewservice.service.impl;

import com.coding.moviereviewservice.enums.Genre;
import com.coding.moviereviewservice.enums.Role;
import com.coding.moviereviewservice.model.Movie;
import com.coding.moviereviewservice.model.UserReview;
import com.coding.moviereviewservice.repository.MovieRepository;
import com.coding.moviereviewservice.service.MovieService;
import com.coding.moviereviewservice.service.ReviewService;
import com.coding.moviereviewservice.service.UserService;
import com.coding.moviereviewservice.util.CustomException;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    private final UserService userService;

    private final ReviewService reviewService;

    private final MovieRepository movieRepository;

    public MovieServiceImpl(UserService userService, ReviewService reviewService, MovieRepository movieRepository) {
        this.userService = userService;
        this.reviewService = reviewService;
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie createMovie(Movie movie) {

        return movieRepository.addData(movie);
    }

    @Override
    public Movie getMovieById(Long movieId) {
        return getMovie(movieId);
    }

    @Override
    public Movie reviewMovie(Long movieId, UserReview userReview) {

        if (canReviewMovie(movieId)) {

            Role userRole = userService.getUserRole(userReview.getUserId());
            userReview.setRole(userRole);
            Movie movie = getMovie(movieId);
            List<UserReview> userReview1 = movie.getUserReview();
            userReview1.add(userReview);
            movie.setUserReview(userReview1);
            return movie;
        }
        throw new CustomException().serviceException();
    }

    private boolean canReviewMovie(Long movieId) {
        Movie movie = getMovie(movieId);
        return Calendar.getInstance().getTime().after(movie.getReleaseDate());

    }

    private Movie getMovie(Long movieId) {
        return movieRepository.getData(movieId).orElseThrow(() -> new CustomException("Data Not Found !!"));
    }

    @Override
    public int getAverageMovieReview(Long movieId) {
        return reviewService.computeAverageReview(getMovie(movieId));
    }

    @Override
    public List<Movie> getTopNCriticMovieReviewByGenre(Genre genre, Integer count) {
        List<Movie> movies = movieRepository.getMovieByGenre(genre);

        Map<Movie, Integer> movieList = movies.stream()
                .collect(Collectors.toMap(movie -> movie, this::getReview));

        return movieList.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .limit(count)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private int getReview(Movie movie) {

        List<UserReview> userReviews = movie.getUserReview().stream()
                .filter(user -> Role.CRITIC.equals(user.getRole()))
                .collect(Collectors.toList());

        return reviewService.getMovieReview(userReviews);
    }
}