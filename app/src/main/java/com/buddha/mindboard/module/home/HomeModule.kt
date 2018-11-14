package com.buddha.mindboard.module.home


import com.buddha.mindboard.di.scope.FragmentScoped

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeModule {
    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun mainFragment(): HomeFragment
}
