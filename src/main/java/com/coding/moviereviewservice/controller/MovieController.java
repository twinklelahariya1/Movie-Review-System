package com.coding.moviereviewservice.controller;

import com.coding.moviereviewservice.model.APIResponse;
import com.coding.moviereviewservice.model.Movie;
import com.coding.moviereviewservice.model.UserReview;
import com.coding.moviereviewservice.service.MovieService;
import com.coding.moviereviewservice.service.ReviewService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    private final MovieService movieService;

    private final ReviewService reviewService;

    public MovieController(MovieService movieService, ReviewService reviewService) {
        this.movieService = movieService;
        this.reviewService = reviewService;
    }

    @PostMapping(path = "")
    public APIResponse createMovie(@RequestBody Movie movie) {
        return APIResponse.success(movieService.createMovie(movie));
    }

    @GetMapping(path = "/{movieId}")
    public APIResponse getUserById(@PathVariable Long movieId) {
        return APIResponse.success(movieService.getMovieById(movieId));
    }

    @GetMapping(path = "/{movieId}/review")
    public APIResponse getMovieReview(@PathVariable Long movieId){
        return APIResponse.success(movieService.getMovieReview(movieId));
    }

    @PostMapping(path = "/{movieId}/review")
    public APIResponse reviewMovie(@RequestBody UserReview userReview, @PathVariable Long movieId) {
        return APIResponse.success(reviewService.reviewMovie(userReview, movieId));
    }
}
