package com.coding.moviereviewservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class Movie {

    private final Long id;

    private final String name;

    private final Date releaseDate;

}
