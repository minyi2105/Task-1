package com.example.fourtitudetask1.task3.mvp.movie_list;

import com.example.fourtitudetask1.task3.model.Search;

import java.util.List;

public interface MovieListContract {
    interface Model {

        interface OnFinishedListener {
            void onSuccess(List<Search> movieArrayList);

            void onFailure(Throwable t);
        }

        void getMovieList(OnFinishedListener onFinishedListener, String searchResult);
    }

    interface View {

        String getSearchInput();

        void showProgress();

        void hideProgress();

        void hideSoftKeyboard();

        void setDataToRecyclerView(List<Search> movieArrayList);

        void onResponseFailure(Throwable throwable);

    }

    interface Presenter {

        void onDestroy();

        void requestDataFromServer();

        void searchButtonClicked();
    }
}
