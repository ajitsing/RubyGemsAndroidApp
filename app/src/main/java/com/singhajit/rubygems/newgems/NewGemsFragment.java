package com.singhajit.rubygems.newgems;

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
import com.singhajit.rubygems.gemlist.GemListRenderer;
import com.singhajit.rubygems.newgems.presenter.NewGemsPresenter;
import com.singhajit.rubygems.trending.model.Gem;
import com.singhajit.rubygems.trending.view.GemsView;

import java.util.ArrayList;

public class NewGemsFragment extends Fragment implements GemsView {
  private NewGemsBinding binding;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    binding = DataBindingUtil.inflate(inflater, R.layout.newgems_fragment, container, false);
    NewGemsPresenter presenter = new NewGemsPresenter((APIClient) getActivity(), this);
    presenter.render();
    return binding.getRoot();
  }

  @Override
  public void render(ArrayList<Gem> gems) {
    GemListRenderer gemListRenderer = new GemListRenderer(gems, binding.recentlyAddedGemsList);
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
