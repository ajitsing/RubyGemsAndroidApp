package com.singhajit.rubygems.search;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.singhajit.rubygems.R;
import com.singhajit.rubygems.core.ErrorHandler;
import com.singhajit.rubygems.core.NetworkActivity;
import com.singhajit.rubygems.databinding.GemSearchBinding;
import com.singhajit.rubygems.gemlist.GemListRenderer;
import com.singhajit.rubygems.search.presenter.GemSearchPresenter;
import com.singhajit.rubygems.search.viewmodel.GemSearchViewModel;
import com.singhajit.rubygems.recent.model.Gem;

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
    GemListRenderer gemListRenderer = new GemListRenderer(gems, binding.gemResults);
    gemListRenderer.render(true);
  }

  @Override
  public void showError(String message) {
    ErrorHandler.showSnackBar(binding.getRoot(), message, new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        presenter.onSearch(gemSearchViewModel);
      }
    });
  }

  private void bindSearchAction() {
    binding.search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
      @Override
      public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        hideKeyboard();
        presenter.onSearch(gemSearchViewModel);
        return true;
      }
    });
  }

  private void hideKeyboard() {
    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    imm.hideSoftInputFromWindow(binding.getRoot().getWindowToken(), 0);
  }
}
