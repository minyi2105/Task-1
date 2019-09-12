package com.example.fourtitudetask1.injection.module;

import android.app.Application;
import android.content.Context;

import com.example.fourtitudetask1.injection.context.ApplicationContext;
import com.example.fourtitudetask1.util.StaticUtil;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    final Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    Application providesApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    StaticUtil provideStaticUtil() {
        return new StaticUtil();
    }
}
