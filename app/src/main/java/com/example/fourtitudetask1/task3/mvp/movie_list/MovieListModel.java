package com.example.fourtitudetask1.task3.mvp.movie_list;

import android.util.Log;

import com.example.fourtitudetask1.task3.api.OmdbHttpClient;
import com.example.fourtitudetask1.task3.api.RetrofitUtil;
import com.example.fourtitudetask1.task3.model.Search;
import com.example.fourtitudetask1.task3.model.SearchApiResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListModel implements MovieListContract.Model {

    private final String TAG = "MovieListModel";

    @Override
    public void getMovieList(OnFinishedListener onFinishedListener, String searchResult) {
        OmdbHttpClient apiService =
                RetrofitUtil.getRetrofitInstance().create(OmdbHttpClient.class);

        Call<SearchApiResponse> call = apiService.getMovieBySearch(OmdbHttpClient.OMDB_API_KEY, searchResult);
        call.enqueue(new Callback<SearchApiResponse>() {
            @Override
            public void onResponse(Call<SearchApiResponse> call, Response<SearchApiResponse> response) {
                SearchApiResponse searchApiResponse = response.body();
                List<Search> movies = new ArrayList<>();

                if (searchApiResponse.getResponse().equals("True")) {
                    movies = response.body().getSearch();
                    Log.d(TAG, "Number of movies received: " + movies.size());

                } else {
                    Log.d(TAG, "No movies found");
                }

                onFinishedListener.onSuccess(movies);
            }

            @Override
            public void onFailure(Call<SearchApiResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                onFinishedListener.onFailure(t);
            }
        });
    }
}
