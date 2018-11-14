package com.buddha.mindboard.module.home

import com.buddha.mindboard.data.datasource.base.BaseViewModel
import com.buddha.mindboard.data.datasource.base.DataSource
import com.buddha.mindboard.data.model.Datum
import com.buddha.mindboard.data.repository.Repository

import io.reactivex.Observable
import retrofit2.Response

class HomeViewModel(private val repository: Repository) : BaseViewModel(), DataSource.Greetings,DataSource {
    override fun getData(): Observable<Response<List<Datum>>>? {
        return repository.getData()
    }

    override fun greetings(): Observable<String> {
        return repository.greetings()
    }
}
