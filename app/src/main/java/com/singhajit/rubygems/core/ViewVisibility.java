package com.singhajit.rubygems.core;

import android.view.View;

public class ViewVisibility {
  private final boolean isVisible;

  public ViewVisibility(boolean isVisible) {
    this.isVisible = isVisible;
  }

  public int getVisibility() {
    return isVisible ? View.VISIBLE : View.GONE;
  }
}
