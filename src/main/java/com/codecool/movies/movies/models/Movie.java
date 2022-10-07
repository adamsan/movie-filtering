package com.codecool.movies.movies.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Movie {
    private int id;
    private String title;
    private String description;
    private Integer releaseYear;
    private String rating;
    private String category;
}
