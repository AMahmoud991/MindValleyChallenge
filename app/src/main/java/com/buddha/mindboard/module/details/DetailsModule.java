package com.buddha.mindboard.module.details;

import com.buddha.mindboard.di.scope.FragmentScoped;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class DetailsModule {
    @FragmentScoped
    @ContributesAndroidInjector
    public abstract DetailsFragment detailsFragment();
}
