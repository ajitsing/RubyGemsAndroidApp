package com.singhajit.rubygems.gemdetails.presenter;

import com.singhajit.rubygems.gemdetails.GemView;
import com.singhajit.rubygems.gemlist.viewmodel.GemViewModel;

public class GemPresenter {
  private final GemView gemView;

  public GemPresenter(GemView gemView) {
    this.gemView = gemView;
  }

  public void openExternalLink(String link) {
    gemView.navigateToExternalLink(link);
  }

  public void shareGem(GemViewModel viewModel) {
    StringBuilder builder = new StringBuilder("Found \"" + viewModel.getName() + "\" gem while browsing through RubyGems android app.\n");
    builder.append("#RubyGemsAndroidApp: goo.gl/Bxz2D1 \n\n");
    builder.append("Gem: https://rubygems.org/gems/" + viewModel.getName() + " \n");
    builder.append("Details: " + viewModel.getInfo() + "\n");

    gemView.openSendApplicationChooser(builder.toString());
  }
}
