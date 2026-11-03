package com.course.dto;

import com.course.MovieRating;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.util.List;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie extends PanacheEntity {
    public String title;
    public String running_time;
    public String release_date;
    public String budget;
    public String box_office;

    @Enumerated(EnumType.STRING)
    public MovieRating rating;

    @ElementCollection(fetch = FetchType.EAGER)
    public List<String> directors;
}
