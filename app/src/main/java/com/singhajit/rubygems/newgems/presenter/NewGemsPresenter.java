package com.singhajit.rubygems.newgems.presenter;

import android.support.annotation.NonNull;

import com.singhajit.rubygems.core.APIClient;
import com.singhajit.rubygems.recent.view.GemsView;

import static com.singhajit.rubygems.core.RubyGemsAPIs.NEWLY_ADDED;

public class NewGemsPresenter extends GemsPresenter {
  public NewGemsPresenter(APIClient apiClient, GemsView view) {
    super(apiClient, view);
  }

  @NonNull
  @Override
  public String getGemsUrl() {
    return NEWLY_ADDED;
  }

}
