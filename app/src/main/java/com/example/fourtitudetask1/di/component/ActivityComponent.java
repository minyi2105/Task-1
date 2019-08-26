package com.example.fourtitudetask1.di.component;

import com.example.fourtitudetask1.activities.MovieDetailActivity;
import com.example.fourtitudetask1.activities.MovieMainActivity;
import com.example.fourtitudetask1.mvp.movie_detail.MovieDetailsContract;
import com.example.fourtitudetask1.mvp.movie_list.MovieListContract;
import com.example.fourtitudetask1.di.module.MvpModule;
import com.example.fourtitudetask1.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = MvpModule.class)
public interface ActivityComponent {
    void inject(MovieMainActivity movieMainActivity);

    MovieListContract.Presenter getMovieMainPresenter();
//
    void inject(MovieDetailActivity movieDetailActivity);

    MovieDetailsContract.Presenter getMovieDetailPresenter();
}
