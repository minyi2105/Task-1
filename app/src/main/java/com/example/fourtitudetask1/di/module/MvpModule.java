package com.example.fourtitudetask1.di.module;

import com.example.fourtitudetask1.mvp.movie_detail.MovieDetailsContract;
import com.example.fourtitudetask1.mvp.movie_detail.MovieDetailsPresenter;
import com.example.fourtitudetask1.mvp.movie_list.MovieListContract;
import com.example.fourtitudetask1.mvp.movie_list.MovieListPresenter;
import com.example.fourtitudetask1.ui.main.TriviaMainFragmentPresenter;
import com.example.fourtitudetask1.ui.questionCount.QuestionCountFragment;
import com.example.fourtitudetask1.ui.questionCount.QuestionCountFragmentMvpView;
import com.example.fourtitudetask1.ui.questionCount.QuestionCountFragmentPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MvpModule {

    private MovieListContract.View movieListView;
    private MovieDetailsContract.View movieDetailsView;
    private QuestionCountFragmentMvpView questionCountFragmentMvpView;

    public MvpModule(MovieListContract.View movieListView) {
        this.movieListView = movieListView;
    }

    public MvpModule(MovieDetailsContract.View movieDetailsView) {
        this.movieDetailsView = movieDetailsView;
    }

    public MvpModule(QuestionCountFragmentMvpView questionCountFragmentMvpView) {
        this.questionCountFragmentMvpView = questionCountFragmentMvpView;
    }

    @Provides
    public MovieListContract.View provideMovieListView() {
        return movieListView;
    }

    @Provides
    public MovieDetailsContract.View provideMovieDetailsView() {
        return movieDetailsView;
    }

    @Provides
    public MovieListContract.Presenter provideMovieListPresenter(MovieListContract.View view) {
        return new MovieListPresenter(view);
    }

    @Provides
    public MovieDetailsContract.Presenter provideMovieDetailsPresenter(MovieDetailsContract.View view) {
        return new MovieDetailsPresenter(view);
    }
}