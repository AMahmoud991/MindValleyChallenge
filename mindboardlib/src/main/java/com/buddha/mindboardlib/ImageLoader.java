package com.buddha.mindboardlib;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;
import com.buddha.mindboardlib.lrucache.LruMemCache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.io.InputStream;

public class ImageLoader extends NetworkFileLoader {
    private @DrawableRes
    int errorDrawable;

    private ImageView imageView;


    public static class Builder extends NetworkFileLoader.Builder<Builder> {
        private @DrawableRes
        int errorDrawable;
        private ImageView imageView;

        public Builder() {
        }

        public Builder errorImage(@DrawableRes int drawable) {
            this.errorDrawable = drawable;
            return this;
        }

        public Builder into(final ImageView imageView) {
            this.imageView = imageView;
            return this;
        }

        public ImageLoader build() {
            if (context == null) {
                throw new RuntimeException("Context can not be null");
            }
            final Bitmap bitmap = LruMemCache.getInstance().getItem(request.url().toString());
            if (bitmap != null) {
                if (context instanceof Activity) {
                    final Activity activity = ((Activity) context);
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImageBitmap(bitmap);
                        }
                    });
                } else {
                    throw new RuntimeException("Invalid context passed.");
                }
            } else {
                if (progressListener != null)
                    progressListener.onRequestStarted();
                okHttpClient.newCall(request)
                        .enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, final IOException e) {
                                e.printStackTrace();
                                if (context instanceof Activity) {
                                    final Activity activity = ((Activity) context);
                                    activity.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            if (progressListener != null)
                                                progressListener.onError(e);
                                            imageView.setImageDrawable(activity.getResources().getDrawable(errorDrawable));
                                        }
                                    });
                                } else {
                                    throw new RuntimeException("Invalid context passed.");
                                }
                            }

                            @Override
                            public void onResponse(Call call, Response response) {
                                InputStream inputStream;
                                if (response.body() != null) {
                                    inputStream = response.body().byteStream();
                                    final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                                    LruMemCache.getInstance().addItem(request.url().toString(), bitmap);
                                    if (context instanceof Activity) {
                                        final Activity activity = ((Activity) context);
                                        activity.runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                if (progressListener != null)
                                                    progressListener.onRequestComplete();
                                                final TransitionDrawable td =
                                                        new TransitionDrawable(new Drawable[]{
                                                                new ColorDrawable(Color.TRANSPARENT),
                                                                new BitmapDrawable(activity.getResources(), bitmap)
                                                        });
                                                imageView.setImageBitmap(bitmap);
                                                imageView.setImageDrawable(td);
                                                td.startTransition(500);
                                            }
                                        });
                                    } else {
                                        throw new RuntimeException("Invalid context passed.");
                                    }
                                }
                            }
                        });
            }
            return new ImageLoader(this);
        }
    }

    protected ImageLoader(Builder builder) {
        super(builder);
        this.errorDrawable = builder.errorDrawable;
        this.imageView = builder.imageView;
    }
}
