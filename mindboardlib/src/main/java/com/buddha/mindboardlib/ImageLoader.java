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
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class ImageLoader extends NetworkFileLoader {
    private static HashMap<String, Bitmap> cache = new HashMap<>();


    private @DrawableRes
    int errorDrawable;

    public static class Builder extends NetworkFileLoader.Builder<Builder> {
        private @DrawableRes
        int errorDrawable;

        public Builder() {
        }

        public Builder errorImage(@DrawableRes int drawable) {
            this.errorDrawable = drawable;
            return this;
        }

        public void into(final ImageView imageView) {
            if (context == null) {
                throw new RuntimeException("Context is null");
            }
            if (cache.containsKey(request.url().toString())) {
                if (context instanceof Activity) {
                    final Activity activity = ((Activity) context);
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImageBitmap(cache.get(request.url().toString()));
                        }
                    });
                } else {
                    throw new RuntimeException("Invalid context passed.");
                }
            } else {
                okHttpClient.newCall(request)
                        .enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                e.printStackTrace();
                                if (context instanceof Activity) {
                                    final Activity activity = ((Activity) context);
                                    activity.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
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
                                    cache.put(request.url().toString(), bitmap);
                                    if (context instanceof Activity) {
                                        final Activity activity = ((Activity) context);
                                        activity.runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                final TransitionDrawable td =
                                                        new TransitionDrawable(new Drawable[] {
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

        }

        public ImageLoader build() {
            return new ImageLoader(this);
        }
    }

    protected ImageLoader(Builder builder) {
        super(builder);
        errorDrawable = builder.errorDrawable;
    }
}
