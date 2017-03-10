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
import com.singhajit.rubygems.core.ErrorHandler;
import com.singhajit.rubygems.databinding.NewGemsBinding;
import com.singhajit.rubygems.gemlist.GemListRenderer;
import com.singhajit.rubygems.newgems.presenter.NewGemsPresenter;
import com.singhajit.rubygems.trending.model.Gem;
import com.singhajit.rubygems.trending.view.GemsView;

import java.util.ArrayList;

import static com.singhajit.rubygems.trending.view.TrendingGemsFragment.GEM_LIST;

public class NewGemsFragment extends Fragment implements GemsView {
  private NewGemsBinding binding;
  private ArrayList<Gem> gems = new ArrayList<>();
  private NewGemsPresenter presenter;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    binding = DataBindingUtil.inflate(inflater, R.layout.newgems_fragment, container, false);
    presenter = new NewGemsPresenter((APIClient) getActivity(), this);
    binding.setPresenter(presenter);
    if (savedInstanceState != null) {
      render(savedInstanceState.<Gem>getParcelableArrayList(GEM_LIST));
    } else {
      presenter.render();
    }
    return binding.getRoot();
  }

  @Override
  public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putParcelableArrayList(GEM_LIST, gems);
  }

  @Override
  public void onResume() {
    super.onResume();
    if (gems.isEmpty()) {
      presenter.render();
    }
  }

  @Override
  public void render(ArrayList<Gem> gems) {
    this.gems = gems;
    GemListRenderer gemListRenderer = new GemListRenderer(gems, binding.recentlyAddedGemsList);
    gemListRenderer.render();
    binding.refreshLayout.setRefreshing(false);
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
