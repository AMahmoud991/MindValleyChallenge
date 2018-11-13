package com.buddha.mindboard.util;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.buddha.mindboard.data.repository.Repository;
import com.buddha.mindboard.module.main.MainViewModel;

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
        if (modelClass.isAssignableFrom(MainViewModel.class))
            return (T) new MainViewModel(repository);
        else
            throw new IllegalArgumentException("ViewModel not found!");
    }
}
