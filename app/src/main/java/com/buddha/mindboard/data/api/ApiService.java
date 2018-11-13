package com.buddha.mindboard.data.api;

import com.buddha.mindboard.data.model.Datum;
import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;

import java.util.List;

/**
 * Created by Buddha Saikia on 13-11-2018.
 */

public interface ApiService {
    @GET("/raw/wgkJgazE")
    Observable<Response<List<Datum>>> getData();
}
