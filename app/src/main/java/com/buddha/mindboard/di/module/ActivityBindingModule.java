package com.buddha.mindboard.di.module;


import com.buddha.mindboard.di.scope.PerActivity;
import com.buddha.mindboard.module.home.HomeActivity;
import com.buddha.mindboard.module.home.HomeModule;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {
    @PerActivity
    @ContributesAndroidInjector(modules = HomeModule.class)
    abstract HomeActivity homeActivity();
}
