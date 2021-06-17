package com.coding.moviereviewservice.controller;

import com.coding.moviereviewservice.enums.Genre;
import com.coding.moviereviewservice.model.APIResponse;
import com.coding.moviereviewservice.model.Movie;
import com.coding.moviereviewservice.model.UserReview;
import com.coding.moviereviewservice.service.MovieService;
import com.coding.moviereviewservice.service.ReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
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
    public APIResponse getAverageMovieReview(@PathVariable Long movieId) {
        return APIResponse.success(movieService.getAverageMovieReview(movieId));
    }

    @GetMapping(path = "/genre/{genre}/critic")
    public APIResponse getTopNCriticReviewOfGenre(@PathVariable String genre, @PathVariable Integer count) {
        try {
            Genre movieGenre = Genre.valueOf(genre.toUpperCase(Locale.ROOT));
            return APIResponse.success(movieService.getTopNCriticMovieReviewByGenre(movieGenre, count));
        } catch (Exception e) {
            return APIResponse.error("Genre does not exist");
        }
    }

    @PostMapping(path = "/{movieId}/review")
    public APIResponse reviewMovie(@RequestBody UserReview userReview, @PathVariable Long movieId) {
        return APIResponse.success(movieService.reviewMovie(movieId, userReview));
    }
}
