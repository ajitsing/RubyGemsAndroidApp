package com.singhajit.rubygems.recent.view;

import com.singhajit.rubygems.core.INotifier;
import com.singhajit.rubygems.recent.model.Gem;

import java.util.ArrayList;

public interface GemsView extends INotifier {
  void render(ArrayList<Gem> gems);

  void showLoader();

  void showPullToRefreshLoader();

  void hideLoader();
}
