package com.buddha.mindboard;

import com.buddha.mindboard.di.components.DaggerAppComponent;
import com.buddha.mindboardlib.lrucache.LruMemCache;
import com.facebook.stetho.Stetho;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class AppController extends DaggerApplication {
    private static AppController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }
        LruMemCache.getInstance().initCache(8*1024*1024);
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }
}
