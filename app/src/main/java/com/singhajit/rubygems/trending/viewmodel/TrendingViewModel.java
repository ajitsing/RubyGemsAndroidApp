package com.singhajit.rubygems.trending.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;

import com.singhajit.rubygems.trending.model.Gem;

import java.util.ArrayList;
import java.util.List;

public class TrendingViewModel extends BaseObservable {
  private List<GemViewModel> gemViewModels = new ArrayList<>();

  public TrendingViewModel(List<Gem> gems) {
    gemViewModels = toGemViewModels(gems);
  }

  @Bindable
  public List<GemViewModel> getGemViewModels() {
    return gemViewModels;
  }

  @NonNull
  private ArrayList<GemViewModel> toGemViewModels(List<Gem> gems) {
    ArrayList<GemViewModel> viewModels = new ArrayList<>();
    for (Gem gem : gems) {
      viewModels.add(new GemViewModel(gem));
    }
    return viewModels;
  }
}
