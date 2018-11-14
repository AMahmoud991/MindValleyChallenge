package com.buddha.mindboard.di.module;


import com.buddha.mindboard.di.scope.PerActivity;
import com.buddha.mindboard.module.details.DetailsActivity;
import com.buddha.mindboard.module.details.DetailsModule;
import com.buddha.mindboard.module.home.HomeActivity;
import com.buddha.mindboard.module.home.HomeModule;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {
    @PerActivity
    @ContributesAndroidInjector(modules = HomeModule.class)
    abstract HomeActivity homeActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = DetailsModule.class)
    abstract DetailsActivity detailsActivity();
}
