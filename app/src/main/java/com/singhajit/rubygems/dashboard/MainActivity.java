package com.singhajit.rubygems.dashboard;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.singhajit.rubygems.R;
import com.singhajit.rubygems.core.INotifier;
import com.singhajit.rubygems.core.NetworkActivity;
import com.singhajit.rubygems.trending.view.TrendingGemsFragment;

public class MainActivity extends NetworkActivity implements INotifier {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
    setupViewPager(viewPager);

    TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
    tabLayout.setupWithViewPager(viewPager);
  }

  private void setupViewPager(ViewPager viewPager) {
    ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
    adapter.addFragment(new ViewPagerFragment(new TrendingGemsFragment(), getString(R.string.trending)));
    adapter.addFragment(new ViewPagerFragment(new UserProfileFragment(), getString(R.string.profile)));
    viewPager.setAdapter(adapter);
  }

  @Override
  public void notify(String message) {
    Snackbar.make(findViewById(R.id.dashboard), message, Snackbar.LENGTH_LONG);
  }
}

