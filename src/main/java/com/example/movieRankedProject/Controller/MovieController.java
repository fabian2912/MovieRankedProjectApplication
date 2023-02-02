package com.example.movieRankedProject.Controller;

import com.example.movieRankedProject.Model.Movie;
import com.example.movieRankedProject.Service.MovieService;
//import jakarta.websocket.server.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/movie-list")
public class MovieController {
    Logger logger = LoggerFactory.getLogger(MovieController.class);

    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ModelAndView get() {
        logger.info("entering get()");
        ModelAndView modelAndView = new ModelAndView();
        List<Movie> movies = movieService.findAllByVotes();
        System.out.println("the movie size (called from controller) is " + movies.size());
        Movie movie = movieService.findById(1L);
        modelAndView.addObject("movie", movie);
        if(movies.size() == 0) {
        } else if (movies.size() > 1){
                logger.info("movies.size > 1 entered");
            if (movieService.findByTitle("default") != null) {
                logger.info("find by title default entered");
                movieService.delete(movieService.findByTitle("default"));
                if (movies.contains(movieService.findByTitle("default"))) {
                    movies.remove(movieService.findByTitle("default"));
                    logger.info("removed movieService.findByTitle(");
                }
                logger.info("find by title default completed");
            }
        }

        modelAndView.addObject("movieList", movies);
        logger.info("exiting get()");
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView get(@PathVariable Long id) {
        logger.info("get with id");
        Movie movie = movieService.findById(id);
        List<Movie> movies = new ArrayList<>();

        movies.add(movie);

        ModelAndView modelAndView = new ModelAndView("movie-summary");
        modelAndView.addObject("movies", movies);
        logger.info("exiting get with id");
        return modelAndView;
    }

    @GetMapping("/summary")
    public ModelAndView summary(@PathParam("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("movie-summary");

        Movie movie = movieService.findById(id);

        modelAndView.addObject("movie", movie);
        System.out.println("going to movie-list/summary with id value " + id + "and movie title " + movie.getTitle());

        if (movieService.findByTitle("default") != null) {
            logger.info("deleting default from Get method");
            movieService.delete(movieService.findByTitle("default"));
        }

        return modelAndView;
    }

    @PostMapping
    public String post(@Valid @ModelAttribute Movie movie, BindingResult result) throws IOException {
        String unrefinedTitle = movie.getTitle();
        String[] splitTitle = unrefinedTitle.split(Pattern.quote(" "));
        String title = "";
        for (int i = 0; i < splitTitle.length; i++) {
            System.out.println("    " + splitTitle[i]);
            String newTitle = splitTitle[i].substring(0,1).toUpperCase() + splitTitle[i].substring(1).toLowerCase();
            title = title + newTitle + " ";
        } // making sure the title is in Title Case to check for uniqueness
        title = title.substring(0, title.length()-1);


        System.err.println("in post with title " + title);

        ModelAndView modelAndView = new ModelAndView();
        MovieSearchParser movieSearchParser = new MovieSearchParser();

        if(result.hasErrors()) {
            System.err.println("result has errors - post");
            modelAndView.setViewName("redirect");
        } else {
            logger.info("result has no errors - post");
            modelAndView.setViewName("redirect");
            logger.info("movie Controller - passing over to MovieSearchParser");

            if (movieService.findByTitle(title) != null) {
                System.err.println("movie " + title + " is already in the database; incrementing the vote counter");
                Movie movie1 = movieService.findByTitle(title);
                movie1.setVotes(movie1.getVotes() + 1);
                movieService.save(movie1);

            } else if (movieService.findByTitle(title) == null) {
                System.err.println("no similar title found");
                Movie movie1 = movieSearchParser.findMovieByTitle(title);
                if (movie1.getTitle() != null && movie1.getCountry() != null) { // checking that api returned valid movie
                    movie1 = movieService.save(movie1);
                    modelAndView.addObject("movie", movie1);
                    System.err.println("valid movie added");
                }
                else {
                    System.err.println("api incorrectly returned title");
                }
            } else {
                System.err.println(title + " is already included in the database");
            }
        }
        logger.info("exiting post");
        return "redirect:movie-list";
    }

    @GetMapping("/edit")
    public ModelAndView edit(@PathParam("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("movie-list");
        Movie movie = movieService.findById(id);
        modelAndView.addObject("movie", movie);
        return modelAndView;
    }

    @PutMapping
    public @ResponseBody String put(@Valid @ModelAttribute Movie movie, BindingResult result) {

        System.err.println("entering put mapping");

        ModelAndView modelAndView = new ModelAndView();
        MovieSearchParser movieSearchParser = new MovieSearchParser();

        if(result.hasErrors()) {
            System.err.println("result has errors - post");
            modelAndView.setViewName("redirect");
        } else {
            System.err.println("updating movie votes in put");
            Movie movie1 = new Movie(movie.getTitle(), movie.getYear(),
                    movie.getCountry(),movie.getRated(),movie.getDirector(),movie.getAwards(),
                    movie.getBoxOffice(),movie.getPlot(),movie.getActors(),movie.getPoster(), movie.getVotes() + 1);
            movieService.delete(movie);
            movieService.save(movie1);
            modelAndView.addObject("movie", movie1);

        }
        return "redirect:movie-list";
    }

    @DeleteMapping
    public @ResponseBody String delete() {
        return "delete";
    }

}