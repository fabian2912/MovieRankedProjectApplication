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
//        ModelAndView modelAndView = new ModelAndView("movie-list");
        ModelAndView modelAndView = new ModelAndView();
//        List<Movie> movies = movieService.findAll();
        List<Movie> movies = movieService.findAll();
        System.out.println("the movie size (called from controller) is " + movies.size());
//        movies.add(movie);
//        movies.add(movie);
//        Movie movie = new Movie(1L, "default", "0000", "0000");
        Movie movie = movieService.findById(1L);
//        movieService.save(movie);
        modelAndView.addObject("movie", movie);
        if(movies.size() == 0) {
//            logger.info("if size == 0 entered");
//            Movie movie = new Movie(1L, "default", "0000", "0000");
//            movieService.save(movie);
//            modelAndView.addObject("movie", movie);
//            logger.info("added movie " + movie.getTitle() + " (should be 'default')");
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

    @PostMapping // @Valid @ModelAttribute
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
//        ModelAndView modelAndView = new ModelAndView("movie-list");
        MovieSearchParser movieSearchParser = new MovieSearchParser();

        if(result.hasErrors()) {
            System.err.println("result has errors - post");
//            modelAndView.setViewName("movie-list");
            modelAndView.setViewName("redirect");
        } else {
            logger.info("result has no errors - post");
//            modelAndView.setViewName("movie-list");
            modelAndView.setViewName("redirect");
            logger.info("movie Controller - passing over to MovieSearchParser");
            Movie movie1 = movieSearchParser.findMovieByTitle(title);
            System.err.println(movieService.findByTitle(title) + "---------------------");

            if (movieService.findByTitle(title) != null) {
                System.err.println("movie " + title + " is already in the database");
            }
            if (movieService.findByTitle(title) == null) {
                System.err.println("no similar title found");
                if (movie1.getTitle() != null && movie1.getCountry() != null) { // checking that api returned valid movie
                    movie1 = movieService.save(movie1);
//            modelAndView.addObject("addMovieTitle", movie.getTitle());
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

//        if (movieService.findByTitle("default") != null) {
//            System.err.println("deleting default from post method");
//            movieService.delete(movieService.findByTitle("default"));
//        }

//        logger.info("called get()");
        return "redirect:movie-list";
    }

//    @PostMapping
//    public ModelAndView post(@Valid @ModelAttribute String title, BindingResult result) throws IOException {
//        MovieSearchParser msp1 = new MovieSearchParser();
//        ModelAndView modelAndView = new ModelAndView();
//
//        if(result.hasErrors()) {
//            modelAndView.setViewName("movies");
//        } else {
//            modelAndView.setViewName("movies");
//            Movie movie = msp1.findMovieByTitle(title);
//            modelAndView.addObject("movie", movie);
//        }
//        return modelAndView;
//    }

    @GetMapping("/edit")
    public ModelAndView edit(@PathParam("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("movie-list");
        Movie movie = movieService.findById(id);
        modelAndView.addObject("movie", movie);
        return modelAndView;
    }

    @PutMapping
    public @ResponseBody String put() {
        return "put";
    }

    @DeleteMapping
    public @ResponseBody String delete() {
        return "delete";
    }

}
