package com.singhajit.rubygems.trending.presenter;

import android.support.annotation.NonNull;

import com.singhajit.rubygems.core.APIClient;
import com.singhajit.rubygems.newgems.presenter.GemsPresenter;
import com.singhajit.rubygems.trending.view.GemsView;

import static com.singhajit.rubygems.core.RubyGemsAPIs.JUST_UPDATED;

public class TrendingPresenter extends GemsPresenter {

  public TrendingPresenter(APIClient apiClient, GemsView view) {
    super(apiClient, view);
  }

  @NonNull
  @Override
  public String getGemsUrl() {
    return JUST_UPDATED;
  }
}