package com.coding.moviereviewservice.service;

import com.coding.moviereviewservice.enums.Genre;
import com.coding.moviereviewservice.enums.Rating;
import com.coding.moviereviewservice.enums.Role;
import com.coding.moviereviewservice.model.Movie;
import com.coding.moviereviewservice.model.UserReview;
import com.coding.moviereviewservice.service.impl.ReviewServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class ReviewServiceTest {

    @Autowired
    ReviewServiceImpl reviewService;

    @Test
    void computeAverageReview() {
        Movie movie = new Movie(1L, "DDLJ", new Date(), Genre.ROMANTIC, new ArrayList<>());
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
        Assertions.assertEquals(reviewService.computeAverageReview(movie), 8);
    }

    @Test
    void getMovieReview() {
        Movie movie = new Movie(1L, "DDLJ", new Date(), Genre.ROMANTIC, new ArrayList<>());
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

        Assertions.assertEquals(reviewService.getMovieReview(movie.getUserReview()),32);
    }
}