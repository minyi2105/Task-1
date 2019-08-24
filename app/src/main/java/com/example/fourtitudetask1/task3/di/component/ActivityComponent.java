package com.example.fourtitudetask1.task3.di.component;

import com.example.fourtitudetask1.activities.MovieMainActivity;
import com.example.fourtitudetask1.task3.di.module.MvpModule;
import com.example.fourtitudetask1.task3.di.scope.ActivityScope;
import com.example.fourtitudetask1.task3.mvp.movie_list.MovieListContract;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = MvpModule.class)
public interface ActivityComponent {
    void inject(MovieMainActivity movieMainActivity);

    MovieListContract.Presenter getMovieMainPresenter();
}
