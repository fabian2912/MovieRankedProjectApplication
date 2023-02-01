package com.example.movieRankedProject.Service;

import com.example.movieRankedProject.Model.Movie;
import com.example.movieRankedProject.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Autowired // note: unnecessary
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie save(Movie movie) {
        movie = movieRepository.saveAndFlush(movie);
        return movie;
    }

    @Override
    public void delete(Movie movie) {
        movieRepository.delete(movie);
    }

    @Override
    public Movie findByTitle(String title) {
        Movie movie = movieRepository.findByTitle(title);
        return movie;
    }

    @Override
    public List<Movie> findAll() {
        List<Movie> movies = movieRepository.findAll();
        System.out.println("movie repo size = " + movies.size());
        return movies;
    }

    @Override
    public Movie findById(Long id) {
        Movie movie = null;
        Optional<Movie> optMovie = movieRepository.findById(id);

        if(optMovie.isPresent()) {
            movie = optMovie.get();
        } else {
            movie = movieRepository.getReferenceById(1L);
        }
        return movie;
    }

    @Override
    public List<Movie> findAllByVotes() {
        List<Movie> list = findAll();
        list.sort(Comparator.comparing(Movie::getVotes));
        Collections.reverse(list);
        return list;
    }
}
