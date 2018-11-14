package com.buddha.mindboard.data.datasource


import com.buddha.mindboard.data.api.ApiService
import com.buddha.mindboard.data.datasource.base.BaseDataSource
import com.buddha.mindboard.data.datasource.base.DataSource

import com.buddha.mindboard.data.model.Datum
import io.reactivex.Observable
import retrofit2.Response

/**
 * Created by Buddha Saikia on 13-11-2018.
 */

class RemoteDataSource(private val apiService: ApiService) : BaseDataSource(), DataSource.Greetings, DataSource {

    override fun greetings(): Observable<String> {
        return Observable.just("Greetings from API sample")
            .compose(this.applySchedulersIO())
    }

    override fun getData(): Observable<Response<List<Datum>>>? {
        return apiService.data
    }
}
