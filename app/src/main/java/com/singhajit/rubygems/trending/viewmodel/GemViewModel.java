package com.singhajit.rubygems.trending.viewmodel;

import com.singhajit.rubygems.trending.model.Gem;

import java.text.NumberFormat;

public class GemViewModel {
  private final Gem gem;

  public GemViewModel(Gem gem) {
    this.gem = gem;
  }

  public String getName() {
    return gem.getName();
  }

  public String getDownloads() {
    return NumberFormat.getInstance().format(gem.getDownloads());
  }

  public String getVersion() {
    return gem.getVersion();
  }

  public String getInfo() {
    return gem.getInfo();
  }
}
