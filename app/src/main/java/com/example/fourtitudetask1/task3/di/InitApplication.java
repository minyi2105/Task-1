package com.example.fourtitudetask1.task3.di;

import android.app.Application;
import android.content.Context;

import com.example.fourtitudetask1.task3.di.component.AppComponent;
import com.example.fourtitudetask1.task3.di.component.DaggerAppComponent;
import com.example.fourtitudetask1.task3.di.module.AppModule;
import com.example.fourtitudetask1.task3.di.module.ContextModule;
import com.example.fourtitudetask1.task3.di.module.DataModule;

public class InitApplication extends Application {

    private AppComponent component;

    public static InitApplication get(Context context) {
        return (InitApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .contextModule(new ContextModule(this))
                .dataModule(new DataModule())
                .build();
    }

    public AppComponent component() {
        return component;
    }
}