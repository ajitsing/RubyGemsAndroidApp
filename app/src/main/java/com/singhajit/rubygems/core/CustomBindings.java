package com.singhajit.rubygems.core;

import android.databinding.BindingAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class CustomBindings {
  @BindingAdapter({"adapter"})
  public static void setRecyclerViewAdapter(RecyclerView view, RecyclerView.Adapter adapter) {
    view.setAdapter(adapter);
    view.setLayoutManager(new LinearLayoutManager(view.getContext()));
  }
}
