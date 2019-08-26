package com.example.fourtitudetask1.di.module;

import com.example.fourtitudetask1.model.SearchApiResponse;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

    @Provides
    public SearchApiResponse provideModelClass() {
        return new SearchApiResponse();
    }
}