package com.singhajit.rubygems.core;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefRepo {
  private final String RUBYGEMS_SHARED_PRED = "RubyGemsSharedPred";
  private final SharedPreferences sharedPreferences;

  public SharedPrefRepo(Context context) {
    sharedPreferences = context.getSharedPreferences(RUBYGEMS_SHARED_PRED, 0);
  }

  public void put(String key, String value) {
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putString(key, value);
    editor.apply();
  }

  public String get(String key) {
    return sharedPreferences.getString(key, null);
  }
}
