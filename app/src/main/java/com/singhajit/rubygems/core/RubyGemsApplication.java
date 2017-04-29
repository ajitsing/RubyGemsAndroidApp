package com.singhajit.rubygems.core;

import android.app.Application;

import com.singhajit.rubygems.network.RubyGemsVolley;
import com.singhajit.sherlock.core.Sherlock;

public class RubyGemsApplication extends Application {
  @Override
  public void onCreate() {
    super.onCreate();
    RubyGemsVolley.getInstance(this).getRequestQueue().start();
    Sherlock.init(this);
  }

  @Override
  public void onTerminate() {
    super.onTerminate();
    RubyGemsVolley.getInstance(this).getRequestQueue().stop();
  }
}
