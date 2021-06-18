package com.coding.moviereviewservice.service.impl;

import com.coding.moviereviewservice.enums.Genre;
import com.coding.moviereviewservice.enums.Role;
import com.coding.moviereviewservice.model.Movie;
import com.coding.moviereviewservice.model.User;
import com.coding.moviereviewservice.model.UserReview;
import com.coding.moviereviewservice.repository.MovieRepository;
import com.coding.moviereviewservice.service.MovieService;
import com.coding.moviereviewservice.service.ReviewService;
import com.coding.moviereviewservice.service.UserService;
import com.coding.moviereviewservice.util.CustomException;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        movieRepository.addData(movie);
        return movie;
    }

    @Override
    public Movie getMovieById(Long movieId) {
        return getMovie(movieId);
    }

    private boolean canReviewMovie(Long movieId) {
        Movie movie = getMovie(movieId);
        return Calendar.getInstance().getTime().after(movie.getReleaseDate());

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

    private Movie getMovie(Long movieId) {
        return movieRepository.getData(movieId).orElseThrow(() -> new CustomException("Data Not Found !!"));
    }

    @Override
    public int getAverageMovieReview(Long movieId) {
        return reviewService.computeReview(getMovie(movieId));
    }

    @Override
    public int getTopNCriticMovieReviewByGenre(Genre genre, Integer count) {
        Stream<Movie> movies = movieRepository.getAllData().stream()
                .filter(movie -> genre.equals(movie.getGenre()));

        return movies
                .map(this::getReview)
                .sorted((o1, o2) -> o2 - o1)
                .limit(count)
                .mapToInt(value -> value)
                .sum();
    }

    private int getReview(Movie movie) {

        List<UserReview> userReviews = movie.getUserReview().stream()
                .filter(user -> Role.CRITIC.equals(user.getRole()))
                .collect(Collectors.toList());

        return reviewService.getMovieReview(userReviews);
    }
}