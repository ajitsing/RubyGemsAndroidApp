package com.singhajit.rubygems.dashboard;

import android.support.v4.app.Fragment;

public class ViewPagerFragment {
  private Fragment fragment;
  private String title;

  public ViewPagerFragment(Fragment fragment, String title) {
    this.fragment = fragment;
    this.title = title;
  }

  public Fragment getFragment() {
    return fragment;
  }

  public String getTitle() {
    return title;
  }
}
