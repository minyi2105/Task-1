package com.example.fourtitudetask1.task3.mvp.movie_detail;

import android.util.Log;

import com.example.fourtitudetask1.task3.api.OmdbHttpClient;
import com.example.fourtitudetask1.task3.api.RetrofitUtil;
import com.example.fourtitudetask1.task3.model.MovieApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailsModel implements MovieDetailsContract.Model {

    private final String TAG = "MovieDetailsModel";

    @Override
    public void getMovieDetails(OnFinishedListener onFinishedListener, String imdbID) {
        OmdbHttpClient apiService =
                RetrofitUtil.getRetrofitInstance().create(OmdbHttpClient.class);

        Call<MovieApiResponse> call = apiService.getMovieById(OmdbHttpClient.OMDB_API_KEY, imdbID);
        call.enqueue(new Callback<MovieApiResponse>() {
            @Override
            public void onResponse(Call<MovieApiResponse> call, Response<MovieApiResponse> response) {

                MovieApiResponse movieApiResponse = response.body();
                Log.d(TAG, "Number of movies received: " + movieApiResponse);

                onFinishedListener.onSuccess(movieApiResponse);
            }

            @Override
            public void onFailure(Call<MovieApiResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
                onFinishedListener.onFailure(t);
            }
        });
    }
}
