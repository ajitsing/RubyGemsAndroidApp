package com.singhajit.rubygems.core;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.singhajit.rubygems.R;

public class CustomBindings {
  @BindingAdapter({"adapter"})
  public static void setRecyclerViewAdapter(RecyclerView view, RecyclerView.Adapter adapter) {
    view.setAdapter(adapter);
    view.setLayoutManager(new LinearLayoutManager(view.getContext()));
  }

  @BindingAdapter({"imageUrl"})
  public static void setImageUrl(NetworkImageView view, String url) {
    view.setImageUrl(url, new ImageLoader(Volley.newRequestQueue(view.getContext()), new ImageLoader.ImageCache() {
      private final LruCache<String, Bitmap> mCache = new LruCache<>(10);

      public void putBitmap(String url, Bitmap bitmap) {
        mCache.put(url, bitmap);
      }

      public Bitmap getBitmap(String url) {
        return mCache.get(url);
      }
    }));
  }

  @BindingAdapter({"onRefresh"})
  public static void setOnRefresh(SwipeRefreshLayout view, SwipeRefreshLayout.OnRefreshListener listener) {
    view.setColorSchemeColors(
        view.getResources().getColor(R.color.colorPrimary),
        view.getResources().getColor(R.color.colorPrimaryDark)
    );
    view.setOnRefreshListener(listener);
  }
}
