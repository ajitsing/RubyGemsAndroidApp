package com.singhajit.rubygems.gemdetails.presenter;

import com.singhajit.rubygems.gemdetails.ExternalLinkNavigator;

public class GemPresenter {
  private final ExternalLinkNavigator externalLinkNavigator;

  public GemPresenter(ExternalLinkNavigator externalLinkNavigator) {
    this.externalLinkNavigator = externalLinkNavigator;
  }

  public void openExternalLink(String link) {
    externalLinkNavigator.navigateToExternalLink(link);
  }
}
