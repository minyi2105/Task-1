package com.example.fourtitudetask1.task3.mvp.movie_list;

import com.example.fourtitudetask1.task3.model.Search;

import java.util.List;

import javax.inject.Inject;

public class MovieListPresenter implements MovieListContract.Presenter, MovieListContract.Model.OnFinishedListener {

    private MovieListContract.View movieListView;
    private MovieListContract.Model movieListModel;

    @Inject
    public MovieListPresenter(MovieListContract.View movieListView) {
        this.movieListView = movieListView;
        this.movieListModel = new MovieListModel();
    }

    @Override
    public void onSuccess(List<Search> movieArrayList) {
        movieListView.setDataToRecyclerView(movieArrayList);
        if (movieListView != null) {
            movieListView.hideProgress();
            if (!movieArrayList.isEmpty()) {
                movieListView.hideEmptyView();
            } else {
                movieListView.showEmptyView();
            }
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
//        movieListModel.getMovieList(this, "Cat");
    }

    @Override
    public void searchButtonClicked() {
        movieListView.hideSoftKeyboard();

        if (movieListView != null) {
            if (movieListView.getSearchInput().isEmpty()) {
//                movieListView.showInputError();
            } else {
                movieListView.showProgress();
                movieListModel.getMovieList(this, movieListView.getSearchInput(), 1);
//                movieListView.showUserSavedMessage();
            }
        }
    }

    @Override
    public void loadMoreMovieList(int page) {
        if (movieListView != null) {
            movieListView.showProgress();
            movieListModel.getMovieList(this, movieListView.getSearchInput(), page);
        }
    }
}
