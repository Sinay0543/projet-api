package com.course.model;

import com.course.MovieRating;
import com.course.dto.Movie;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.util.List;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@NamedQueries({
    @NamedQuery(name = "Movie.getByDirector", query = "select m from MovieModel m join m.attributes.directors d where d like ?1")
})
public class MovieModel extends PanacheEntity {
    @JsonProperty("id")
    public String jsonId;
    public String type;

    @OneToOne(cascade = CascadeType.ALL)
    public Movie attributes = new Movie();

    public static List<MovieModel> findByRating(String rating) {
        return find("attributes.rating", MovieRating.valueOf(rating)).list();
    }

    public static List<MovieModel> findByDirectorLike(String director) {
        return find("#Movie.getByDirector", director).list();
    }
}
