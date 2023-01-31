package com.example.movieRankedProject.Service;

import com.example.movieRankedProject.Model.Movie;

import java.util.List;

public interface MovieService {
    Movie save(Movie movie);

    List<Movie> findAll();

    Movie findById(Long id);

    void delete(Movie movie);

    Movie findByTitle(String title);
}
