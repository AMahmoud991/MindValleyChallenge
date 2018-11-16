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

class RemoteDataSource(private val apiService: ApiService) : BaseDataSource(), DataSource {

    override fun getData(): Observable<Response<List<Datum>>>? {
        return apiService.data.compose(applySchedulersIO())
    }
}
