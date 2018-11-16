package com.buddha.mindboard.util

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

import com.buddha.mindboard.data.repository.Repository
import com.buddha.mindboard.module.home.HomeViewModel

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CustomViewModelFactory @Inject
constructor(private val repository: Repository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HomeViewModel::class.java))
            HomeViewModel(repository) as T
        else
            throw IllegalArgumentException("ViewModel not found!")
    }
}
