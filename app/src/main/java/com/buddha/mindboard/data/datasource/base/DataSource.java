package com.buddha.mindboard.data.datasource.base;


import com.buddha.mindboard.data.model.Datum;
import io.reactivex.Observable;
import retrofit2.Response;

import java.util.List;

/**
 * Created by Buddha Saikia on 13-11-2018.
 */

public interface DataSource {

    interface Greetings{
        Observable<String> greetings();
    }
    Observable<Response<List<Datum>>> getData();
}
