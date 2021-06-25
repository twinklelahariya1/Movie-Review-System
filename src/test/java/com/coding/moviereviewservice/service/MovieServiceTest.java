package com.coding.moviereviewservice.service;

import com.coding.moviereviewservice.enums.Genre;
import com.coding.moviereviewservice.enums.Rating;
import com.coding.moviereviewservice.enums.Role;
import com.coding.moviereviewservice.model.Movie;
import com.coding.moviereviewservice.model.UserReview;
import com.coding.moviereviewservice.repository.MovieRepository;
import com.coding.moviereviewservice.service.impl.MovieServiceImpl;
import com.coding.moviereviewservice.util.CustomException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

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

    @Test
    void getAverageMovieReview() {
        UserReview userReview1 = new UserReview(1L, Role.VIEWER, Rating.NINE);
        UserReview userReview2 = new UserReview(2L, Role.CRITIC, Rating.FIVE);
        UserReview userReview3 = new UserReview(3L, Role.ADMIN, Rating.TWO);
        UserReview userReview4 = new UserReview(4L, Role.VIEWER, Rating.FIVE);

        List<UserReview> userReview = movie.getUserReview();
        userReview.add(userReview1);
        userReview.add(userReview2);
        userReview.add(userReview3);
        userReview.add(userReview4);

        movie.setUserReview(userReview);

        when(movieRepository.getData(Mockito.any())).thenReturn(Optional.of(movie));
        when(reviewService.computeAverageReview(movie)).thenReturn(8);
        Assertions.assertEquals(movieService.getAverageMovieReview(1L), 8);
    }

    @Test
    void getAverageMovieReviewMovieNotFound() {
        when(movieRepository.getData(Mockito.any())).thenReturn(Optional.empty());
        when(reviewService.computeAverageReview(movie)).thenReturn(0);
        Assertions.assertThrows(CustomException.class, () -> movieService.getAverageMovieReview(1L));
    }

    @Test
    void getTopNCriticMovieReviewByGenre() {
        UserReview userReview1 = new UserReview(1L, Role.CRITIC, Rating.NINE);
        UserReview userReview2 = new UserReview(2L, Role.CRITIC, Rating.FIVE);
        UserReview userReview3 = new UserReview(3L, Role.CRITIC, Rating.TWO);
        UserReview userReview4 = new UserReview(4L, Role.VIEWER, Rating.FIVE);

        List<UserReview> userReview = movie.getUserReview();
        userReview.add(userReview1);
        userReview.add(userReview2);
        userReview.add(userReview4);

        movie.setUserReview(userReview);

        Movie movie2 = new Movie(2L, "HHHK", new Date(), Genre.ROMANTIC, new ArrayList<>());
        List<UserReview> userReviewFor2 = movie.getUserReview();
        userReview.add(userReview1);
        userReview.add(userReview2);
        userReview.add(userReview3);
        userReview.add(userReview4);

        movie2.setUserReview(userReviewFor2);

        when(movieRepository.getMovieByGenre(Genre.ROMANTIC)).thenReturn(Arrays.asList(movie, movie2));

        when(reviewService.getMovieReview(Mockito.any())).thenReturn(28).thenReturn(32);

        Assertions.assertEquals(movieService.getTopNCriticMovieReviewByGenre(Genre.ROMANTIC,1),Collections.singletonList(movie2));

    }
}