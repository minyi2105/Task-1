package com.example.fourtitudetask1;

import android.content.Context;

import androidx.multidex.MultiDexApplication;

import com.example.fourtitudetask1.injection.component.AppComponent;
import com.example.fourtitudetask1.injection.component.DaggerAppComponent;
import com.example.fourtitudetask1.injection.module.AppModule;
import com.example.fourtitudetask1.util.StaticUtil;

import javax.inject.Inject;

public class AppApplication extends MultiDexApplication {

    @Inject
    StaticUtil mStaticUtil;

    private AppComponent mAppComponent;

    public static AppComponent getAppComponent(Context mContext) {
        AppApplication app = (AppApplication) mContext.getApplicationContext();
        return app.mAppComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        mAppComponent.inject(this);
    }
}
