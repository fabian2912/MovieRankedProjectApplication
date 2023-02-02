package com.example.movieRankedProject.test;

import com.example.movieRankedProject.Model.Movie;
import com.example.movieRankedProject.Repository.MovieRepository;
import com.example.movieRankedProject.Service.MovieService;
import com.example.movieRankedProject.Service.MovieServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class movieTest {

    MovieService uut;

    MovieRepository repository;

    Movie mockMovie;

    long movieId;

    Movie actualMovie;

    Iterable<Movie> actualMovieIterable;

    @BeforeEach
    public void setUp() {
        repository = mock(MovieRepository.class);
        mockMovie = mock(Movie.class);
        uut = new MovieServiceImpl(repository);
    }

    @Test
    public void testGetMovieById() {
        movieId = 1;
        actualMovie = uut.findById(movieId);
        verify(repository,times(1)).findById(movieId);
    }

    @Test
    public void testGetMovieByTitle() {
        movieId = 1;
        String movieTitle = "Shrek";
        actualMovie = uut.findByTitle(movieTitle);
        verify(repository,times(1)).findByTitle(movieTitle);
    }


    @Test
    public void testFindAllMovies() {
        actualMovieIterable = uut.findAll();
        verify(repository,times(1)).findAll();
    }


    @Test
    public void testSortingMovieList() {
        List<Movie> movieList = new ArrayList<>();

        Movie movie = new Movie();
        movie.setTitle("Movie");
        movie.setVotes(5);
        movieList.add(movie);

        Movie movie1 = new Movie();
        movie1.setTitle("Movie1");
        movie1.setVotes(10);
        movieList.add(movie1);

        Movie movie2 = new Movie();
        movie2.setTitle("Movie2");
        movie2.setVotes(7);
        movieList.add(movie2);

        when(repository.findAll()).thenReturn(movieList);


        List<Movie> movieListByVote = uut.findAllByVotes();
        verify(repository,times(1)).findAll();
        assertEquals(movie1,movieListByVote.get(0));
    }

    @Test
    public void testDelete() {
        uut.delete(mockMovie);
        verify(repository,times(1)).delete(mockMovie);
    }

    @Test
    public void testSave() {
        actualMovie = uut.save(mockMovie);
        verify(repository,times(1)).saveAndFlush(mockMovie);
    }


}
