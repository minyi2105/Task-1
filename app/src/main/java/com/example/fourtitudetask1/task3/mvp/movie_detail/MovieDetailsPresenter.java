package com.example.fourtitudetask1.task3.mvp.movie_detail;

import com.example.fourtitudetask1.task3.model.MovieApiResponse;

public class MovieDetailsPresenter implements MovieDetailsContract.Presenter, MovieDetailsContract.Model.OnFinishedListener {

    private MovieDetailsContract.View movieDetailsView;
    private MovieDetailsContract.Model movieDetailsModel;

    public MovieDetailsPresenter(MovieDetailsContract.View movieDetailView) {
        this.movieDetailsView = movieDetailView;
        this.movieDetailsModel = new MovieDetailsModel();
    }

    @Override
    public void onDestroy() {
        movieDetailsView = null;
    }

    @Override
    public void requestMovieData(String imdbID) {
        if (movieDetailsView != null) {
            movieDetailsView.showProgress();
        }
        movieDetailsModel.getMovieDetails(this, imdbID);
    }

    @Override
    public void onSuccess(MovieApiResponse movieApiResponse) {
        if (movieDetailsView != null) {
            movieDetailsView.hideProgress();
        }
        movieDetailsView.setDataToViews(movieApiResponse);
    }

    @Override
    public void onFailure(Throwable t) {
        if (movieDetailsView != null) {
            movieDetailsView.hideProgress();
        }
        movieDetailsView.onResponseFailure(t);
    }
}
