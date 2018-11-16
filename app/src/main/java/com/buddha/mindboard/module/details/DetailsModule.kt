package com.buddha.mindboard.module.details

import com.buddha.mindboard.di.scope.FragmentScoped
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DetailsModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun detailsFragment(): DetailsFragment
}
