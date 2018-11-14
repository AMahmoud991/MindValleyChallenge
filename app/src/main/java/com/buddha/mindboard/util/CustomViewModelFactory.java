package com.buddha.mindboard.util;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.buddha.mindboard.data.repository.Repository;
import com.buddha.mindboard.module.home.HomeViewModel;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CustomViewModelFactory implements ViewModelProvider.Factory {
    private final Repository repository;

    @Inject
    public CustomViewModelFactory(Repository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(HomeViewModel.class))
            return (T) new HomeViewModel(repository);
        else
            throw new IllegalArgumentException("ViewModel not found!");
    }
}
