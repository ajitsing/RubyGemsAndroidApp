package com.singhajit.rubygems.helpandfeedback.presenter;

import com.singhajit.rubygems.helpandfeedback.WebsiteNavigator;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HelpAndFeedbackPresenterTest {
  @Test
  public void shouldNavigateToWebsite() throws Exception {
    WebsiteNavigator websiteNavigator = mock(WebsiteNavigator.class);
    HelpAndFeedbackPresenter presenter = new HelpAndFeedbackPresenter(websiteNavigator);

    presenter.navigateToWebsite("http://www.singhajit.com");

    verify(websiteNavigator).openInBrowser("http://www.singhajit.com");
  }
}