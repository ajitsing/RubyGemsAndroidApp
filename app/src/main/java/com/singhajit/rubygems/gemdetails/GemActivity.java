package com.singhajit.rubygems.gemdetails;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.singhajit.rubygems.R;
import com.singhajit.rubygems.databinding.GemBinding;
import com.singhajit.rubygems.gemlist.viewmodel.GemViewModel;
import com.singhajit.rubygems.trending.model.Gem;

public class GemActivity extends AppCompatActivity {
  public static final String GEM_EXTRA = "GEM_EXTRA";

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Gem gem = getIntent().getParcelableExtra(GEM_EXTRA);
    GemBinding binding = DataBindingUtil.setContentView(this, R.layout.gem_activity);
    binding.setViewModel(new GemViewModel(gem));

    Toolbar toolbar = binding.toolbar;
    setSupportActionBar(toolbar);

    getSupportActionBar().setHomeButtonEnabled(true);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }
}
