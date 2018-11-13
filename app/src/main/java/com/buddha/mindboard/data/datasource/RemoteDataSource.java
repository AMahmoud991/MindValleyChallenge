package com.buddha.mindboard.data.datasource;


import com.buddha.mindboard.data.api.ApiService;
import com.buddha.mindboard.data.datasource.base.BaseDataSource;
import com.buddha.mindboard.data.datasource.base.DataSource;

import com.buddha.mindboard.data.model.Datum;
import io.reactivex.Observable;
import retrofit2.Response;

import java.util.List;

/**
 * Created by Buddha Saikia on 13-11-2018.
 */

public class RemoteDataSource extends BaseDataSource implements DataSource.Greetings,DataSource {

    private ApiService apiService;

    public RemoteDataSource(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Observable<String> greetings() {
        return Observable.just("Greetings from API sample")
                .compose(this.<String>applySchedulersIO());
    }

    @Override
    public Observable<Response<List<Datum>>> getData() {
        return apiService.getData();
    }
}
