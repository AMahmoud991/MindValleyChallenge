package com.buddha.mindboard.di.module;


import com.buddha.mindboard.di.scope.PerActivity;
import com.buddha.mindboard.module.main.MainActivity;
import com.buddha.mindboard.module.main.MainModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {
    @PerActivity
    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity mainActivity();
}
