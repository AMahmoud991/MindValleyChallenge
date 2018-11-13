package com.buddha.mindboard.module.main

import com.buddha.mindboard.data.datasource.base.BaseViewModel
import com.buddha.mindboard.data.datasource.base.DataSource
import com.buddha.mindboard.data.repository.Repository

import io.reactivex.Observable

class MainViewModel(private val repository: Repository) : BaseViewModel(), DataSource.Greetings {

    override fun greetings(): Observable<String> {
        return repository.greetings()
    }
}
