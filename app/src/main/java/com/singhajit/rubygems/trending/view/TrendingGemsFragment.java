package com.singhajit.rubygems.trending.view;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.singhajit.rubygems.R;
import com.singhajit.rubygems.core.APIClient;
import com.singhajit.rubygems.core.INotifier;
import com.singhajit.rubygems.databinding.TrendingBinding;
import com.singhajit.rubygems.gemlist.GemListRenderer;
import com.singhajit.rubygems.trending.model.Gem;
import com.singhajit.rubygems.trending.presenter.TrendingPresenter;

import java.util.ArrayList;

public class TrendingGemsFragment extends Fragment implements TrendingView {

  private TrendingBinding binding;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    binding = DataBindingUtil.inflate(inflater, R.layout.trending_fragment, container, false);
    TrendingPresenter presenter = new TrendingPresenter((APIClient) getActivity(), this);
    presenter.render();
    return binding.getRoot();
  }

  @Override
  public void render(ArrayList<Gem> gems) {
    GemListRenderer gemListRenderer = new GemListRenderer(gems, binding.recentlyUpdatedGemsList);
    gemListRenderer.render();
  }

  @Override
  public void showLoader() {
    binding.progressBar.setVisibility(View.VISIBLE);
  }

  @Override
  public void hideLoader() {
    binding.progressBar.setVisibility(View.GONE);
  }

  @Override
  public void notify(String message) {
    ((INotifier) getActivity()).notify(message);
  }
}
