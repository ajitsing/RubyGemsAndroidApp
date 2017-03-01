package com.singhajit.rubygems.trending.view;

import com.singhajit.rubygems.core.INotifier;
import com.singhajit.rubygems.trending.model.Gem;

import java.util.ArrayList;

public interface TrendingView extends INotifier {
  void render(ArrayList<Gem> gems);

  void showLoader();

  void hideLoader();
}
