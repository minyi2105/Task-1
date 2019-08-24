package com.example.fourtitudetask1.task3.di.module;

import com.example.fourtitudetask1.task3.mvp.movie_list.MovieListContract;
import com.example.fourtitudetask1.task3.mvp.movie_list.MovieListPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MvpModule {

    private MovieListContract.View movieListView;

    public MvpModule(MovieListContract.View movieListView) {
        this.movieListView = movieListView;
    }

    @Provides
    public MovieListContract.View provideView() {
        return movieListView;
    }

    @Provides
    public MovieListContract.Presenter providePresenter(MovieListContract.View view) {
        return new MovieListPresenter(view);
    }

//    @Provides
//    public MainContract.PresenterCallBack providePresenter(MainContract.ViewCallBack view, Model model) {
//        return new MainPresenterImpl(view, model);
//    }
}