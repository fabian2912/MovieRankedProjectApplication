package com.example.movieRankedProject.Model;

public class MovieDTO {
    private Long id;
    private String Title;

    private int Year;

    private String Country;

    public MovieDTO(Long id) {
        this.id = id;
    }

    public MovieDTO(Long id, String Title, String Country, int Year) {
        this.id = id;
        this.Title = Title;
        this.Country = Country;
        this.Year = Year;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return Title;
    }

    public int getYear() {
        return Year;
    }

    public String getCountry() {
        return Country;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setYear(int year) {
        Year = year;
    }

    public void setCountry(String country) {
        Country = country;
    }
}
