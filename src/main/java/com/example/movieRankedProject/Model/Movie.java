package com.example.movieRankedProject.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table
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

    private int votes = 1;

    public Movie(String title, String year, String country, String rated, String director, String awards, String boxOffice, String plot, String actors, int votes) {
        Title = title;
        Year = year;
        Country = country;
        Rated = rated;
        Director = director;
        Awards = awards;
        BoxOffice = boxOffice;
        Plot = plot;
        Actors = actors;
        this.votes = votes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
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

    public String getRated() {
        return Rated;
    }

    public void setRated(String rated) {
        Rated = rated;
    }

    public String getDirector() {
        return Director;
    }

    public void setDirector(String director) {
        Director = director;
    }

    public String getAwards() {
        return Awards;
    }

    public void setAwards(String awards) {
        Awards = awards;
    }

    public String getPlot() {
        return Plot;
    }

    public void setPlot(String plot) {
        Plot = plot;
    }

    public String getBoxOffice() {
        return BoxOffice;
    }

    public void setBoxOffice(String boxOffice) {
        BoxOffice = boxOffice;
    }

    public String getActors() {
        return Actors;
    }

    public void setActors(String actors) {
        Actors = actors;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }
}
