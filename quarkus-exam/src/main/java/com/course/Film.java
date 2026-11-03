package com.course;

public class Film {
    private String title;
    private MovieRating rating;

    public Film() {}
    public Film(String title, MovieRating rating) {
        this.title = title;
        this.rating = rating;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public MovieRating getRating() { return rating; }
    public void setRating(MovieRating rating) { this.rating = rating; }
}
