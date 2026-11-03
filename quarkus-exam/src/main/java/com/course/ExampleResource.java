package com.course;

import com.course.api.MoviesApi;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/hello")
public class ExampleResource {

    @Inject
    @RestClient
    MoviesApi moviesApi;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from Quarkus REST";
    }

    @GET
    @Path("/movies")
    public String getMovies() {
        return moviesApi.getMovies();
    }

    @GET
    @Path("/movies/{id}")
    public String getMovie(@PathParam("id") String id) {
        return moviesApi.getMovie(id);
    }
}
