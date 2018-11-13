package com.buddha.mindboard.data.datasource.base;


import io.reactivex.Observable;

/**
 * Created by Buddha Saikia on 06-10-2018.
 */

public interface DataSource {

    interface Greetings{
        Observable<String> greetings();
    }

}
