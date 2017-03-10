package com.singhajit.rubygems.core;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefRepo {
  public static final String API_KEY = "API_KEY";
  private final String RUBYGEMS_SHARED_PREF = "RubyGemsSharedPref";
  private final SharedPreferences sharedPreferences;

  public SharedPrefRepo(Context context) {
    sharedPreferences = context.getSharedPreferences(RUBYGEMS_SHARED_PREF, 0);
  }

  public void put(String key, String value) {
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putString(key, value);
    editor.apply();
  }

  public String get(String key) {
    return sharedPreferences.getString(key, null);
  }

  public void remove(String key) {
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.remove(key);
    editor.apply();
  }
}
