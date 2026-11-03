package com.course.api;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@RegisterRestClient(baseUri = "https://api.potterdb.com")
@Path("/v1/movies")
@Produces(MediaType.APPLICATION_JSON)
public interface MoviesApi {
    @GET
    String getMovies();

    @GET
    @Path("/{id}")
    String getMovie(@PathParam("id") String id);
}

