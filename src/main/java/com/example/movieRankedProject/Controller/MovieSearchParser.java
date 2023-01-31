package com.example.movieRankedProject.Controller;

import com.example.movieRankedProject.Model.Movie;
import com.fasterxml.jackson.databind.ObjectMapper;
//import io.github.bonigarcia.wdm.online.HttpClient;
//import org.apache.hc.client5.http.classic.methods.HttpGet;
//import org.apache.hc.client5.http.classic.methods.HttpUriRequest;
//import org.apache.hc.client5.http.classic.methods.HttpGet;
//import org.apache.hc.client5.http.classic.methods.HttpUriRequest;
import org.apache.hc.client5.http.impl.classic.BasicHttpClientResponseHandler;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
//import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
//import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.http.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
//import java.net.http.HttpClient;
//import java.net.http.HttpResponse;
import java.util.regex.Pattern;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;

public class MovieSearchParser {

    public Movie findMovieByTitle (String title) throws IOException {

        Logger logger = LoggerFactory.getLogger(MovieSearchParser.class);
        logger.info("entering findMovieByTitle; input title");
        logger.info("title = " + title);

        String[] splitTitle = title.split(Pattern.quote(" "));

        System.out.println("split title:");
        for (int i = 0; i < splitTitle.length; i++) {
            System.out.println("    " + splitTitle[i]);
        }

        String validTitle = "";
        for(String t : splitTitle) {
            validTitle = validTitle + t + "+";
            logger.info("findMovieByTitle for loop");
        }
        System.out.println(validTitle);

        if (validTitle.length() > 0) {
            if (validTitle.substring(validTitle.length() - 1).equals("+")) {
                validTitle = validTitle.substring(0, validTitle.length() - 1);
                logger.info("findMovieByTitle inner if block");
                System.out.println(validTitle);
            }
        }

        HttpUriRequest request = new HttpGet("https://omdbapi.com/?t=" + validTitle + "&type=movie&apikey=4abf25ed");
//        HttpUriRequest request = new HttpGet("https://omdbapi.com/?t=War+of+the+Worlds&type=movie&apikey=4abf25ed");
        logger.info(String.valueOf(request));
        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse httpResponse;
        httpResponse = client.execute(request);
        logger.info(httpResponse.toString());

        ObjectMapper mapper = new ObjectMapper();
        Movie movie = mapper.readValue(httpResponse.getEntity().getContent(), Movie.class);
        if (movie == null) {
            logger.info("movie is null");
        } else {
            System.out.println(movie.getTitle());
        }
        logger.info("completed mapping; returning movie - movieSearchParser");
        logger.info("movie info: " + movie.getTitle() + " " + movie.getCountry() + " " + movie.getYear() + " and id = " + movie.getId());

//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(DUMMY_URL))
//                .build();//        HttpUriRequest request = new HttpGet("https://omdbapi.com/?t=War+of+the+Worlds&type=movie&apikey=4abf25ed");
//        logger.info(String.valueOf(request));
////        CloseableHttpClient client = HttpClients.createDefault();
//        String response = client.execute(request, new BasicHttpClientResponseHandler());
//        logger.debug("Response -> {}", response);
//
//        ObjectMapper mapper = new ObjectMapper();
//        Movie movie = new Movie();
////        Movie movie = mapper.readValue(response.getEntity().getContent(), Movie.class);
//        logger.info("completed mapping; returning movie - movieSearchParser");
//        logger.info("movie info: " + movie.getTitle() + " " + movie.getCountry() + " " + movie.getYear() + " and id = " + movie.getId());

        return movie;

    }

}
