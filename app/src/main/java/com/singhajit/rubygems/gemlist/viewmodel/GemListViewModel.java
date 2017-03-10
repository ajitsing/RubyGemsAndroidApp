package com.singhajit.rubygems.gemlist.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;

import com.singhajit.rubygems.core.StringResolver;
import com.singhajit.rubygems.recent.model.Gem;

import java.util.ArrayList;
import java.util.List;

public class GemListViewModel extends BaseObservable {
  private List<GemViewModel> gemViewModels = new ArrayList<>();

  public GemListViewModel(List<Gem> gems, StringResolver stringResolver) {
    gemViewModels = toGemViewModels(gems, stringResolver);
  }

  @Bindable
  public List<GemViewModel> getGemViewModels() {
    return gemViewModels;
  }

  @NonNull
  private ArrayList<GemViewModel> toGemViewModels(List<Gem> gems, StringResolver stringResolver) {
    ArrayList<GemViewModel> viewModels = new ArrayList<>();
    for (Gem gem : gems) {
      viewModels.add(new GemViewModel(gem, stringResolver));
    }
    return viewModels;
  }
}
