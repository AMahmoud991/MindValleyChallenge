package com.buddha.mindboard.data.datasource;


import com.buddha.mindboard.data.datasource.base.BaseDataSource;
import com.buddha.mindboard.data.datasource.base.DataSource;

import io.reactivex.Observable;

/**
 * Created by Buddha Saikia on 06-10-2018.
 */

public class LocalDataSource extends BaseDataSource implements DataSource.Greetings {

    public LocalDataSource() {

    }

    @Override
    public Observable<String> greetings() {
        return null;
    }
}
