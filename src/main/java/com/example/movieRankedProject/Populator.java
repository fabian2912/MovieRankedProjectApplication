package com.example.movieRankedProject;

import com.example.movieRankedProject.Controller.MovieSearchParser;
import com.example.movieRankedProject.Model.Movie;
import com.example.movieRankedProject.Repository.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Populator implements CommandLineRunner {

    MovieRepository movieRepository;

    public Populator(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        MovieSearchParser movieSearchParser = new MovieSearchParser();
        Movie defaultMovie = movieSearchParser.findMovieByTitle("shrek");
//        Movie defaultMovie = new Movie("default","default","default","default","default","default","default","default","default");
        movieRepository.save(defaultMovie);
    }
}