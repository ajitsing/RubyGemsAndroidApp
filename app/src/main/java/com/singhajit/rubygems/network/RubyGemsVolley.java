package com.singhajit.rubygems.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class RubyGemsVolley {
  private static RubyGemsVolley instance;
  private static Context context;
  private RequestQueue requestQueue;
  private ImageLoader imageLoader;

  private RubyGemsVolley(Context context) {
    RubyGemsVolley.context = context;
    requestQueue = getRequestQueue();

    imageLoader = new ImageLoader(requestQueue,
        new ImageLoader.ImageCache() {
          private final LruCache<String, Bitmap>
              cache = new LruCache<>(20);

          @Override
          public Bitmap getBitmap(String url) {
            return cache.get(url);
          }

          @Override
          public void putBitmap(String url, Bitmap bitmap) {
            cache.put(url, bitmap);
          }
        });
  }

  public static synchronized RubyGemsVolley getInstance(Context context) {
    if (instance == null) {
      instance = new RubyGemsVolley(context);
    }
    return instance;
  }

  public RequestQueue getRequestQueue() {
    if (requestQueue == null) {
      requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }
    return requestQueue;
  }

  public void addToRequestQueue(BaseRequest request) {
    request.setTag(request.getUrl());
    getRequestQueue().cancelAll(request.getUrl());
    getRequestQueue().add(request);
  }

  public ImageLoader getImageLoader() {
    return imageLoader;
  }
}
