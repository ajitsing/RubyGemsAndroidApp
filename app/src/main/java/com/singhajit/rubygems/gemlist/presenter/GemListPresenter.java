package com.singhajit.rubygems.gemlist.presenter;

import com.singhajit.rubygems.gemlist.GemListAction;
import com.singhajit.rubygems.gemlist.viewmodel.GemViewModel;

public class GemListPresenter {

  private final GemListAction gemListAction;

  public GemListPresenter(GemListAction gemListAction) {
    this.gemListAction = gemListAction;
  }

  public void onGemClicked(GemViewModel gemViewModel) {
    gemListAction.navigateToGemPage(gemViewModel.getGem());
  }
}
