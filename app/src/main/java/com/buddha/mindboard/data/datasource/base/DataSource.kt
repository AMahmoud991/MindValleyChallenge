package com.buddha.mindboard.data.datasource.base


import com.buddha.mindboard.data.model.Datum
import io.reactivex.Observable
import retrofit2.Response

/**
 * Created by Buddha Saikia on 13-11-2018.
 */

interface DataSource {
    fun getData(): Observable<Response<List<Datum>>>?

    interface Greetings {
        fun greetings(): Observable<String>
    }
}
