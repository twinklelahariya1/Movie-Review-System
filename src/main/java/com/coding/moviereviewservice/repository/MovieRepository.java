package com.coding.moviereviewservice.repository;

import com.coding.moviereviewservice.enums.Genre;
import com.coding.moviereviewservice.model.Movie;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class MovieRepository {

    Map<Long, Movie> movies = new HashMap<>();

    public Movie addData(Movie movie) {
        movies.put(movie.getId(), movie);
        return movie;
    }

    public Optional<Movie> getData(Long id) {
        return Optional.ofNullable(movies.get(id));
    }

    public List<Movie> getAllData() {
        return new ArrayList<>(movies.values());
    }

    public List<Movie> getMovieByGenre(Genre genre) {
        return movies.values().stream().filter(movie -> genre.equals(movie.getGenre()))
                .collect(Collectors.toList());
    }

}
