package com.buddha.mindboard.data.repository

import com.buddha.mindboard.data.datasource.LocalDataSource
import com.buddha.mindboard.data.datasource.RemoteDataSource
import com.buddha.mindboard.data.datasource.base.BaseDataSource
import com.buddha.mindboard.data.datasource.base.DataSource
import com.buddha.mindboard.data.model.Datum
import io.reactivex.Observable
import retrofit2.Response

import javax.inject.Inject
import javax.inject.Singleton

import dagger.internal.Preconditions.checkNotNull

/**
 * Created by Buddha Saikia on 13-11-2018.
 */
@Singleton
class Repository @Inject
constructor(
    remoteDataSource: RemoteDataSource,
    localDataSource: LocalDataSource
) : BaseDataSource(), DataSource.Greetings, DataSource {

    private val remoteDataSource: RemoteDataSource = checkNotNull(remoteDataSource)
    private val localDataSource: LocalDataSource = checkNotNull(localDataSource)

    override fun greetings(): Observable<String> {
        return remoteDataSource.greetings()
    }

    override fun getData(): Observable<Response<List<Datum>>>? {
        return remoteDataSource.getData()?.compose(this.applySchedulersIO())
    }
}
