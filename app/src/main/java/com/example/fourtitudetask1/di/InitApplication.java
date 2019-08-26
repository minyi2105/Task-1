package com.example.fourtitudetask1.di;

import android.app.Application;
import android.content.Context;

import com.example.fourtitudetask1.di.component.AppComponent;
import com.example.fourtitudetask1.di.module.AppModule;
import com.example.fourtitudetask1.di.module.ContextModule;
import com.example.fourtitudetask1.di.module.DataModule;
import com.example.fourtitudetask1.di.component.DaggerAppComponent;

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