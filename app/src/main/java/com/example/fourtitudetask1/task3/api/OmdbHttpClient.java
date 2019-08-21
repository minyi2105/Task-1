package com.example.fourtitudetask1.task3.api;

import com.example.fourtitudetask1.task3.model.MovieApiResponse;
import com.example.fourtitudetask1.task3.model.SearchApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OmdbHttpClient {
    String OMDB_API_KEY = "4864e30f";

//    @GET()
//    Observable<MovieApiResponse> getMovieByTitle(@Query("apikey") String apiKey,
//                                                 @Query("t") String movieTitle);
//
//    @GET()
//    Observable<MovieApiResponse> getMovieById(@Query("apikey") String apiKey,
//                                              @Query("i") String imdbId);
//
//    @GET()
//    Observable<SearchApiResponse> getMovieBySearch(@Query("apikey") String apiKey,
//                                                 @Query("s") String searchInput);

    @GET(".")
    Call<MovieApiResponse> getMovieByTitle(@Query("apikey") String apiKey,
                                           @Query("t") String movieTitle);

    @GET(".")
    Call<MovieApiResponse> getMovieById(@Query("apikey") String apiKey,
                                              @Query("i") String imdbId);

    @GET(".")
    Call<SearchApiResponse> getMovieBySearch(@Query("apikey") String apiKey,
                                             @Query("s") String searchInput);
}
