package com.buddha.mindboard.module.main


import com.buddha.mindboard.di.scope.FragmentScoped

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainModule {
    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun mainFragment(): MainFragment
}
