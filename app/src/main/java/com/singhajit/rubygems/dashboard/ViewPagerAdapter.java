package com.singhajit.rubygems.dashboard;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
  private List<ViewPagerFragment> fragments = new ArrayList<>();

  public ViewPagerAdapter(FragmentManager manager) {
    super(manager);
  }

  @Override
  public Fragment getItem(int position) {
    return fragments.get(position).getFragment();
  }

  @Override
  public int getCount() {
    return fragments.size();
  }

  @Override
  public CharSequence getPageTitle(int position) {
    return fragments.get(position).getTitle();
  }

  public void addFragment(ViewPagerFragment viewPagerFragment) {
    this.fragments.add(viewPagerFragment);
  }
}
