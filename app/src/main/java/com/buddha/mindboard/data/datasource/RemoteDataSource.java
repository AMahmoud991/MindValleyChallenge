package com.buddha.mindboard.data.datasource;


import com.buddha.mindboard.data.api.ApiService;
import com.buddha.mindboard.data.datasource.base.BaseDataSource;
import com.buddha.mindboard.data.datasource.base.DataSource;

import io.reactivex.Observable;

/**
 * Created by Buddha Saikia on 06-10-2018.
 */

public class RemoteDataSource extends BaseDataSource implements DataSource.Greetings {

    private ApiService apiService;

    public RemoteDataSource(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Observable<String> greetings() {
        return Observable.just("Greetings from API sample")
                .compose(this.<String>applySchedulersIO());
    }
}
