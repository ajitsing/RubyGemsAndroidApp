package com.singhajit.rubygems.helpandfeedback.presenter;

import com.singhajit.rubygems.helpandfeedback.WebsiteNavigator;

public class HelpAndFeedbackPresenter {
  private final WebsiteNavigator navigator;

  public HelpAndFeedbackPresenter(WebsiteNavigator navigator) {
    this.navigator = navigator;
  }

  public void navigateToWebsite(String url) {
    navigator.openInBrowser(url);
  }
}
