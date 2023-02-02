package com.example.movieRankedProject.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@Data
@AllArgsConstructor
//@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonProperty("Title")
    private String Title;

    @JsonProperty("Year")
    private String Year;

    @JsonProperty("Country")
    private String Country;

    @JsonProperty("Rated")
    private String Rated;

    @JsonProperty("Director")
    private String Director;

    @JsonProperty("Awards")
    private String Awards;

    @JsonProperty("BoxOffice")
    private String BoxOffice;

    @JsonProperty("Plot")
    private String Plot;

    @JsonProperty("Actors")
    private String Actors;

    @JsonProperty("Poster")
    private String Poster;

    private int votes = 1;

    public Movie(String title, String year, String country, String rated, String director, String awards, String boxOffice, String plot, String actors, String poster, int votes) {
        Title = title;
        Year = year;
        Country = country;
        Rated = rated;
        Director = director;
        Awards = awards;
        BoxOffice = boxOffice;
        Plot = plot;
        Actors = actors;
        Poster = poster;
        this.votes = votes;
    }


    public Movie(Long id, String title, String year, String country) {
        this.id = id;
        this.Title = title;
        this.Year = year;
        this.Country = country;
    }

    public Movie(Long id, String title) {
        this.id = id;
        this.Title = title;
    }

    public Movie(String title) {
        Title = title;
        System.out.println("constructor with only title input");
    }

    public Movie() {
        System.out.println("default empty constructor");
    }

}
