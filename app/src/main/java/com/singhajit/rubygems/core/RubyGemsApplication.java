package com.singhajit.rubygems.core;

import android.app.Application;

import com.singhajit.rubygems.network.RubyGemsVolley;

public class RubyGemsApplication extends Application {
  @Override
  public void onCreate() {
    super.onCreate();
    RubyGemsVolley.getInstance(this).getRequestQueue().start();
  }

  @Override
  public void onTerminate() {
    super.onTerminate();
    RubyGemsVolley.getInstance(this).getRequestQueue().stop();
  }
}
