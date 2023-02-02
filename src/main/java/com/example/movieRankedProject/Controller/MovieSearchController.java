package com.example.movieRankedProject.Controller;

import com.example.movieRankedProject.Model.Movie;
import com.example.movieRankedProject.Service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.Optional;
import java.util.regex.Pattern;

@RestController
@CrossOrigin
@RequestMapping("/movie")
public class MovieSearchController {

    Logger logger = LoggerFactory.getLogger(MovieSearchController.class);

    @Autowired
    private MovieService movieService;
//
//    @GetMapping
//    public Movie get(){
//        return new Movie();
//    }

    @GetMapping(value = {"/{movieTitle}"})
    public Movie  getSearchedMovie(@PathVariable String movieTitle) throws IOException {
        System.out.println("Get reached");
        String unrefinedTitle = movieTitle;
        String[] splitTitle = unrefinedTitle.split(Pattern.quote(" "));
        String title = "";
        for (int i = 0; i < splitTitle.length; i++) {
            System.out.println("    " + splitTitle[i]);
            String newTitle = splitTitle[i].substring(0,1).toUpperCase() + splitTitle[i].substring(1).toLowerCase();
            title = title + newTitle + " ";
        } // making sure the title is in Title Case to check for uniqueness
        title = title.substring(0, title.length()-1);


        System.err.println("in post with title " + title);

        MovieSearchParser movieSearchParser = new MovieSearchParser();


            logger.info("movie Controller - passing over to MovieSearchParser");

            Movie movie1 = null;

            if (movieService.findByTitle(title) != null) {
                System.err.println("movie " + title + " is already in the database; incrementing the vote counter");
                movie1 = movieService.findByTitle(title);

            } else if (movieService.findByTitle(title) == null) {
                System.err.println("no similar title found");
                movie1 = movieSearchParser.findMovieByTitle(title);
                if (movie1.getTitle() != null && movie1.getCountry() != null) { // checking that api returned valid movie
                    movie1 = movieService.save(movie1);
                    System.err.println("valid movie added");
                }
                else {
                    System.err.println("api incorrectly returned title");
                }
            } else {
                System.err.println(title + " is already included in the database");
            }
        logger.info("exiting post");
        return movie1;
        }

    }

