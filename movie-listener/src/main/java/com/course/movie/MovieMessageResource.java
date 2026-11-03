package com.course.movie;

import com.course.Film;
import com.course.MovieRating;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Path("/api/movies-message")
public class MovieMessageResource {

    private final List<Film> films = new CopyOnWriteArrayList<>();

    @Incoming("movie-in")
    public void processMessage(Film film) {
        films.add(film);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Film> getFilms() {
        return films;
    }

    @GET
    @Path("/rating/{rating}")
    @Produces(MediaType.APPLICATION_JSON)
    public long countByRating(@PathParam("rating") MovieRating rating) {
        return films.stream().filter(f -> f.getRating() == rating).count();
    }
}
