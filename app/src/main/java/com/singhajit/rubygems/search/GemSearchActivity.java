package com.singhajit.rubygems.search;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.widget.TextView;

import com.singhajit.rubygems.R;
import com.singhajit.rubygems.core.NetworkActivity;
import com.singhajit.rubygems.databinding.GemSearchBinding;
import com.singhajit.rubygems.gemlist.GemListFragment;
import com.singhajit.rubygems.search.presenter.GemSearchPresenter;
import com.singhajit.rubygems.search.viewmodel.GemSearchViewModel;
import com.singhajit.rubygems.trending.model.Gem;

import java.util.ArrayList;

public class GemSearchActivity extends NetworkActivity implements GemSearchView {

  private GemSearchBinding binding;
  private GemSearchPresenter presenter;
  private GemSearchViewModel gemSearchViewModel;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = DataBindingUtil.setContentView(this, R.layout.gem_search_activity);

    Toolbar toolbar = binding.toolbar;
    toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_black_24dp);
    setSupportActionBar(toolbar);

    getSupportActionBar().setHomeButtonEnabled(true);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    gemSearchViewModel = new GemSearchViewModel();
    binding.setViewModel(gemSearchViewModel);
    presenter = new GemSearchPresenter(this, this);

    bindSearchAction();
  }

  @Override
  public void renderResults(ArrayList<Gem> gems) {
    GemListFragment fragment = new GemListFragment();
    Bundle args = new Bundle();
    args.putParcelableArrayList(GemListFragment.GEM_LIST, gems);
    fragment.setArguments(args);
    getSupportFragmentManager().beginTransaction().replace(R.id.gem_results, fragment).commit();
  }

  @Override
  public void showError(String message) {
    Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_LONG).show();
  }

  private void bindSearchAction() {
    binding.search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
      @Override
      public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        presenter.onSearch(gemSearchViewModel);
        return true;
      }
    });
  }
}
