package com.example.oren.menus.webservice;

import com.example.oren.menus.data.Movie;
import com.example.oren.menus.data.MoviesList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Webservice {
    /**
     * @GET declares an HTTP GET request
     * @Path("movie") annotation on the userId parameter marks it as a
     * replacement for the {user} placeholder in the @GET path
     */
    @GET("movie/{movie}")
    Call<Movie> getMovie(@Path("movie") long movieId);

    @GET("movie/popular")
    Call<MoviesList> getPopularMovies();
}