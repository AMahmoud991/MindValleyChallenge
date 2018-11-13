package com.buddha.mindboard.data.repository;

import android.content.Context;
import android.support.annotation.NonNull;
import com.buddha.mindboard.data.datasource.LocalDataSource;
import com.buddha.mindboard.data.datasource.RemoteDataSource;
import com.buddha.mindboard.data.datasource.base.BaseDataSource;
import com.buddha.mindboard.data.datasource.base.DataSource;
import com.buddha.mindboard.data.model.Datum;
import io.reactivex.Observable;
import retrofit2.Response;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

import static dagger.internal.Preconditions.checkNotNull;

/**
 * Created by Buddha Saikia on 13-11-2018.
 */
@Singleton
public class Repository extends BaseDataSource implements DataSource.Greetings, DataSource {

    @NonNull
    private final RemoteDataSource remoteDataSource;
    @NonNull
    private final LocalDataSource localDataSource;

    @Inject
    public Repository(@NonNull RemoteDataSource awRemoteDataSource,
                      @NonNull LocalDataSource awLocalDataSource) {
        this.remoteDataSource = checkNotNull(awRemoteDataSource);
        this.localDataSource = checkNotNull(awLocalDataSource);
    }

    @Override
    public Observable<String> greetings() {
        return remoteDataSource.greetings();
    }

    @Override
    public Observable<Response<List<Datum>>> getData() {
        return remoteDataSource.getData().compose(this.<Response<List<Datum>>>applySchedulersIO());
    }
}
