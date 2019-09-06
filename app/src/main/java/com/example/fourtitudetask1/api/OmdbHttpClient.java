package com.example.fourtitudetask1.api;

import com.example.fourtitudetask1.model.MovieApiResponse;
import com.example.fourtitudetask1.model.SearchApiResponse;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OmdbHttpClient {
    String OMDB_API_KEY = "4864e30f";

    @GET(".")
    Call<MovieApiResponse> getMovieByTitle(@Query("apikey") String apiKey,
                                           @Query("t") String movieTitle);

    @GET(".")
    Observable<MovieApiResponse> getMovieById(@Query("apikey") String apiKey,
                                              @Query("i") String imdbId);

    @GET(".")
    Observable<SearchApiResponse> getMovieBySearch(@Query("apikey") String apiKey,
                                                   @Query("s") String searchInput,
                                                   @Query("page") int page);
}
