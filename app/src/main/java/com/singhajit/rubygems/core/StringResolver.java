package com.singhajit.rubygems.core;

import android.content.Context;
import android.support.annotation.StringRes;

public class StringResolver {

  private final Context context;

  public StringResolver(Context context) {
    this.context = context;
  }

  public String getString(@StringRes int resourceId, Object... args) {
    Object[] arg = args;
    return context.getString(resourceId, args);
  }
}
