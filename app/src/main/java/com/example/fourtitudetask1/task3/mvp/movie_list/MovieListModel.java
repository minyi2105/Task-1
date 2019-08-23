package com.example.fourtitudetask1.task3.mvp.movie_list;

import android.util.Log;

import com.example.fourtitudetask1.task3.api.OmdbHttpClient;
import com.example.fourtitudetask1.task3.api.RetrofitUtil;
import com.example.fourtitudetask1.task3.model.Search;
import com.example.fourtitudetask1.task3.model.SearchApiResponse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MovieListModel implements MovieListContract.Model {

    private final String TAG = "MovieListModel";
    private List<Search> movieList;

    @Override
    public void getMovieList(OnFinishedListener onFinishedListener, String searchResult) {

        OmdbHttpClient apiService =
                RetrofitUtil.getRetrofitInstance().create(OmdbHttpClient.class);

        apiService.getMovieBySearch(OmdbHttpClient.OMDB_API_KEY, searchResult)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SearchApiResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SearchApiResponse searchApiResponse) {
                        movieList = new ArrayList<>(searchApiResponse.getSearch());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.toString());
                        onFinishedListener.onFailure(e);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "Number of movies received: " + movieList.size());
                        onFinishedListener.onSuccess(movieList);
                    }
                });


//        Call<SearchApiResponse> call = apiService.getMovieBySearch(OmdbHttpClient.OMDB_API_KEY, searchResult);
//        call.enqueue(new Callback<SearchApiResponse>() {
//            @Override
//            public void onResponse(Call<SearchApiResponse> call, Response<SearchApiResponse> response) {
//                SearchApiResponse searchApiResponse = response.body();
//                List<Search> movies = new ArrayList<>();
//
//                if (searchApiResponse.getResponse().equals("True")) {
//                    movies = response.body().getSearch();
//                    Log.d(TAG, "Number of movies received: " + movies.size());
//
//                } else {
//                    Log.d(TAG, "No movies found");
//                }
//
//                onFinishedListener.onSuccess(movies);
//            }
//
//            @Override
//            public void onFailure(Call<SearchApiResponse> call, Throwable t) {
//                // Log error here since request failed
//                Log.e(TAG, t.toString());
//                onFinishedListener.onFailure(t);
//            }
//        });
    }
}
