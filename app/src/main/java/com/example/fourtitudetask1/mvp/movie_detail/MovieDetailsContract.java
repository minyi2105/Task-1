package com.example.fourtitudetask1.mvp.movie_detail;

import com.example.fourtitudetask1.model.MovieApiResponse;

public interface MovieDetailsContract {
    interface Model {
        interface OnFinishedListener {
            void onSuccess(MovieApiResponse movieApiResponse);

            void onFailure(Throwable t);
        }

        void getMovieDetails(OnFinishedListener onFinishedListener, String imdbID);
    }

    interface View {
        void showProgress();

        void hideProgress();

        void setDataToViews(MovieApiResponse movieApiResponse);

        void onResponseFailure(Throwable throwable);
    }

    interface Presenter {
        void onDestroy();

        void requestMovieData(String imdbID);
    }
}
