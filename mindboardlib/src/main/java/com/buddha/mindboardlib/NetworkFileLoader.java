package com.buddha.mindboardlib;

import android.content.Context;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;

public class NetworkFileLoader {
    private OkHttpClient okHttpClient;
    private Request request;
    private Context context;

    public static class Builder<T extends Builder<T>> {
        Request request;
        Context context;
        OkHttpClient okHttpClient;

        public Builder() {
            okHttpClient = new OkHttpClient();
        }

        public T from(Context context) {
            this.context = context;
            return (T) this;
        }

        public T load(String url) throws IOException {
            if (url == null) {
                url = "";
            }
            request = new Request.Builder().url(url).build();
            return (T) this;
        }

        public NetworkFileLoader build() {
            return new NetworkFileLoader(this);
        }
    }

    public NetworkFileLoader(Builder<?> builder) {
        okHttpClient = builder.okHttpClient;
        context = builder.context;
        request = builder.request;
    }
}
