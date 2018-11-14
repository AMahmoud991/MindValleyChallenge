package com.buddha.mindboardlib;

import android.content.Context;
import android.util.Log;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class NetworkFileLoader {
    public interface ProgressListener {
        void onRequestStarted();

        void onRequestComplete();
    }

    private OkHttpClient okHttpClient;
    private Request request;
    private Context context;
    private ProgressListener progressListener;

    public static class Builder<T extends Builder<T>> {
        Request request;
        Context context;
        OkHttpClient okHttpClient;
        ProgressListener progressListener;

        public Builder() {
            okHttpClient = new OkHttpClient();
        }

        public T from(Context context) {
            this.context = context;
            return (T) this;
        }

        public T load(String url) {
            Log.d("imageUrl", url);
            if (url == null || url.equals("") || !url.startsWith("http") || !url.startsWith("https")) {
                throw new IllegalArgumentException("Invalid image url");
            }
            request = new Request.Builder().url(url).build();
            return (T) this;
        }

        public T setProgressListener(ProgressListener progressListener) {
            this.progressListener = progressListener;
            return (T) this;
        }

        public NetworkFileLoader build() {
            return new NetworkFileLoader(this);
        }
    }

    public NetworkFileLoader(Builder<?> builder) {
        this.okHttpClient = builder.okHttpClient;
        this.context = builder.context;
        this.request = builder.request;
        this.progressListener = builder.progressListener;
    }
}
