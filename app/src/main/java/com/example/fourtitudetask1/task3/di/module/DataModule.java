package com.example.fourtitudetask1.task3.di.module;

import com.example.fourtitudetask1.task3.model.SearchApiResponse;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

    @Provides
    public SearchApiResponse provideModelClass() {
        return new SearchApiResponse();
    }
}