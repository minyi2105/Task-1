package com.example.fourtitudetask1.task3.mvp.movie_list;

import com.example.fourtitudetask1.task3.model.Search;

import java.util.List;

public class MovieListPresenter implements MovieListContract.Presenter, MovieListContract.Model.OnFinishedListener {

    private MovieListContract.View movieListView;
    private MovieListContract.Model movieListModel;

    public MovieListPresenter(MovieListContract.View movieListView) {
        this.movieListView = movieListView;
        movieListModel = new MovieListModel();
    }

    @Override
    public void onSuccess(List<Search> movieArrayList) {
        movieListView.setDataToRecyclerView(movieArrayList);
        if (movieListView != null) {
            movieListView.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        movieListView.onResponseFailure(t);
        if (movieListView != null) {
            movieListView.hideProgress();
        }
    }

    @Override
    public void onDestroy() {
        this.movieListView = null;
    }
    @Override
    public void requestDataFromServer() {
        if (movieListView != null) {
            movieListView.showProgress();
        }
        movieListModel.getMovieList(this, "Cat");
    }

    @Override
    public void searchButtonClicked() {
        movieListView.hideSoftKeyboard();

        if (movieListView != null) {
            if (movieListView.getSearchInput().isEmpty()) {
//                movieListView.showInputError();
            } else {
                movieListView.showProgress();
                movieListModel.getMovieList(this, movieListView.getSearchInput());
//                movieListView.showUserSavedMessage();
            }

        }
    }
}
