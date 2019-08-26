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
    private SearchApiResponse movie;

    @Override
    public void getMovieList(OnFinishedListener onFinishedListener, String searchResult, int page) {

        OmdbHttpClient apiService =
                RetrofitUtil.getRetrofitInstance().create(OmdbHttpClient.class);

        apiService.getMovieBySearch(OmdbHttpClient.OMDB_API_KEY, searchResult, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SearchApiResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SearchApiResponse searchApiResponse) {

                        if (searchApiResponse.getResponse().equals("True")) {
//                            if (page == 1) {
                                movieList = new ArrayList<>(searchApiResponse.getSearch());
//                            } else {
//                                movieList.addAll(searchApiResponse.getSearch());
//                            }

                            movie = searchApiResponse;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.toString());
                        onFinishedListener.onFailure(e);
                    }

                    @Override
                    public void onComplete() {
                        onFinishedListener.onSuccess(movieList, movie);
                    }
                });
    }
}
