package com.singhajit.rubygems.core;

import android.databinding.BindingAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.toolbox.NetworkImageView;
import com.singhajit.rubygems.R;
import com.singhajit.rubygems.network.RubyGemsVolley;

public class CustomBindings {
  @BindingAdapter({"adapter"})
  public static void setRecyclerViewAdapter(RecyclerView view, RecyclerView.Adapter adapter) {
    view.setAdapter(adapter);
    view.setLayoutManager(new LinearLayoutManager(view.getContext()));
  }

  @BindingAdapter({"imageUrl"})
  public static void setImageUrl(NetworkImageView view, String url) {
    view.setImageUrl(url, RubyGemsVolley.getInstance(view.getContext()).getImageLoader());
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
