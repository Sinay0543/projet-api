package com.course;

import com.course.model.MovieModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import java.io.IOException;
import java.util.List;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class MovieResource {

    @Inject
    @Channel("movie-out")
    Emitter<Film> emitter;

    @GET
    @Path("/load")
    @Transactional
    public List<MovieModel> load() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<MovieModel> list = objectMapper
                .readerForListOf(MovieModel.class)
                .readValue(getClass().getClassLoader().getResourceAsStream("movies.json"));
        for (MovieModel movie : list) {
            movie.persist();
            emitter.send(new Film(movie.attributes.title, movie.attributes.rating));
        }
        return list;
    }

    // GET on /movies
    @GET
    @Path("/movies")
    public List<MovieModel> findAll() {
        return MovieModel.listAll();
    }

    // GET on /movies/{id}
    @GET
    @Path("/movies/{id}")
    public MovieModel findById(@PathParam("id") Long id) {
        return MovieModel.findById(id);
    }

    // GET on /ratings/{rating}
    @GET
    @Path("/ratings/{rating}")
    public List<MovieModel> findByRating(@PathParam("rating") MovieRating rating) {
        return MovieModel.findByRating(rating.name());
    }

    // GET on /ratings-count/{rating}
    @GET
    @Path("/ratings-count/{rating}")
    public long countByRating(@PathParam("rating") MovieRating rating) {
        return MovieModel.count("attributes.rating", rating);
    }

    // GET on /directors/{director}
    @GET
    @Path("/directors/{director}")
    public List<MovieModel> findByDirectorLike(@PathParam("director") String director) {
        // Use a @NamedQuery on MovieModel for this query
        return MovieModel.findByDirectorLike(director);
    }
}
