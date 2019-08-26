package com.example.fourtitudetask1.task3.mvp.movie_list;

import com.example.fourtitudetask1.task3.model.Search;
import com.example.fourtitudetask1.task3.model.SearchApiResponse;

import java.util.List;

public interface MovieListContract {
    interface Model {

        interface OnFinishedListener {
            void onSuccess(List<Search> movieArrayList, SearchApiResponse searchApiResponse);

            void onFailure(Throwable t);
        }

        void getMovieList(OnFinishedListener onFinishedListener, String searchResult, int page);
    }

    interface View {

        String getSearchInput();

        void showProgress();

        void hideProgress();

        void hideSoftKeyboard();

        void setDataToRecyclerView(List<Search> movieArrayList);

        void onResponseFailure(Throwable throwable);

        void showEmptyView();

        void hideEmptyView();

        void setSearchApiResponse(SearchApiResponse searchApiResponse);

    }

    interface Presenter {

        void onDestroy();

        void requestDataFromServer();

        void searchButtonClicked();

        void loadMoreMovieList(int page);
    }
}
