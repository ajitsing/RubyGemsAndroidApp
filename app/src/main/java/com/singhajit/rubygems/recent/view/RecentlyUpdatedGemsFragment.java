package com.singhajit.rubygems.recent.view;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.singhajit.rubygems.R;
import com.singhajit.rubygems.core.APIClient;
import com.singhajit.rubygems.core.ErrorHandler;
import com.singhajit.rubygems.databinding.TrendingBinding;
import com.singhajit.rubygems.gemlist.GemListRenderer;
import com.singhajit.rubygems.newgems.presenter.GemsPresenter;
import com.singhajit.rubygems.recent.model.Gem;
import com.singhajit.rubygems.recent.presenter.RecentlyUpdatedGemsPresenter;

import java.util.ArrayList;

public class RecentlyUpdatedGemsFragment extends Fragment implements GemsView {

  public static final String GEM_LIST = "GEM_LIST";
  private TrendingBinding binding;
  private ArrayList<Gem> gems = new ArrayList<>();
  private GemsPresenter presenter;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    binding = DataBindingUtil.inflate(inflater, R.layout.trending_fragment, container, false);
    presenter = new RecentlyUpdatedGemsPresenter((APIClient) getActivity(), this);
    binding.setPresenter(presenter);
    if (savedInstanceState != null) {
      render(savedInstanceState.<Gem>getParcelableArrayList(GEM_LIST));
    } else {
      presenter.render();
    }
    return binding.getRoot();
  }

  @Override
  public void onResume() {
    super.onResume();
    if (gems.isEmpty()) {
      presenter.render();
    }
  }

  @Override
  public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putParcelableArrayList(GEM_LIST, gems);
  }

  @Override
  public void render(ArrayList<Gem> gems) {
    boolean isRefresh = !this.gems.isEmpty();
    this.gems = gems;
    GemListRenderer gemListRenderer = new GemListRenderer(this.gems, binding.recentlyUpdatedGemsList);
    gemListRenderer.render(isRefresh);
    binding.refreshLayout.setRefreshing(false);
  }

  @Override
  public void showPullToRefreshLoader() {
    binding.refreshLayout.setRefreshing(true);
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
    binding.refreshLayout.setRefreshing(false);
    ErrorHandler.showSnackBar(binding.getRoot(), message, new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        presenter.render();
      }
    });
  }
}
