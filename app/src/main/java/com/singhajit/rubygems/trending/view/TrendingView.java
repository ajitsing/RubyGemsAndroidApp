package com.singhajit.rubygems.trending.view;

import com.singhajit.rubygems.core.INotifier;
import com.singhajit.rubygems.trending.viewmodel.TrendingViewModel;

public interface TrendingView extends INotifier {
  void render(TrendingViewModel trendingViewModel);
  void showLoader();
  void hideLoader();
}
