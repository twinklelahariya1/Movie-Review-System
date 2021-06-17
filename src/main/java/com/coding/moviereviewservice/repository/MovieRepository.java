package com.coding.moviereviewservice.repository;

import com.coding.moviereviewservice.model.Movie;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MovieRepository {

    Map<Long, Movie> movies = new HashMap<>();

    public void addData(Movie movie) {
        movies.put(movie.getId(), movie);
    }

    public Optional<Movie> getData(Long id) {
        return Optional.ofNullable(movies.get(id));
    }

    public List<Movie> getAllData() {
        return new ArrayList<>(movies.values());
    }


}
