package com.coding.moviereviewservice.service;

import com.coding.moviereviewservice.enums.Genre;
import com.coding.moviereviewservice.enums.Rating;
import com.coding.moviereviewservice.enums.Role;
import com.coding.moviereviewservice.model.Movie;
import com.coding.moviereviewservice.model.User;
import com.coding.moviereviewservice.model.UserReview;
import com.coding.moviereviewservice.repository.MovieRepository;
import com.coding.moviereviewservice.service.impl.MovieServiceImpl;
import com.coding.moviereviewservice.util.CustomException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
class MovieServiceTest {

    @Autowired
    MovieServiceImpl movieService;

    @MockBean
    MovieRepository movieRepository;

    @MockBean
    UserService userService;

    @MockBean
    ReviewService reviewService;

    Movie movie;

    @BeforeEach
    void init() {
        movie = new Movie(1L, "DDLJ", new Date(), Genre.ROMANTIC, new ArrayList<>());
    }

    @Test
    void createMovie() {
        when(movieRepository.addData(movie)).thenReturn(movie);
        Assertions.assertEquals(movieService.createMovie(movie).getGenre(), Genre.ROMANTIC);
    }

    @Test
    void getMovieById() {
        when(movieRepository.getData(Mockito.any())).thenReturn(Optional.of(movie));
        Assertions.assertEquals(movieService.getMovieById(movie.getId()).getGenre(), Genre.ROMANTIC);
    }

    @Test
    void getMovieByIdNotFound() {
        when(movieRepository.getData(Mockito.any())).thenReturn(Optional.empty());
        Assertions.assertThrows(CustomException.class, () -> movieService.getMovieById(movie.getId()));
    }

    @Test
    void reviewMovieHoneyMoonFlow() {
        UserReview userReview = new UserReview(1L, Rating.EIGHT);
        when(movieRepository.getData(Mockito.any())).thenReturn(Optional.of(movie));
        when(userService.getUserRole(Mockito.any())).thenReturn(Role.VIEWER);
        Assertions.assertEquals(movieService.reviewMovie(1L, userReview).getId(), 1L);
    }

    @Test
    void reviewMovieMovieNotFound() {
        UserReview userReview = new UserReview(1L, Rating.EIGHT);
        when(movieRepository.getData(Mockito.any())).thenReturn(Optional.empty());
        when(userService.getUserRole(Mockito.any())).thenReturn(Role.VIEWER);
        Assertions.assertThrows(CustomException.class, () -> movieService.reviewMovie(1L, userReview));
    }

    @Test
    void reviewMovieDateBeforeRelease() {
        Date myDate = new Date(2014, 02, 11);
        movie = new Movie(1L, "DDLJ", myDate, Genre.ROMANTIC, new ArrayList<>());
        UserReview userReview = new UserReview(1L, Rating.EIGHT);
        when(movieRepository.getData(Mockito.any())).thenReturn(Optional.of(movie));
        when(userService.getUserRole(Mockito.any())).thenReturn(Role.VIEWER);
        Assertions.assertThrows(CustomException.class, () -> movieService.reviewMovie(1L, userReview));
    }
}