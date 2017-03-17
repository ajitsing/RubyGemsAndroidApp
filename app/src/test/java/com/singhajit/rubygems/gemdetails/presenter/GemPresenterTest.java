package com.singhajit.rubygems.gemdetails.presenter;

import com.singhajit.rubygems.gemdetails.GemView;
import com.singhajit.rubygems.gemlist.viewmodel.GemViewModel;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GemPresenterTest {

  private GemPresenter presenter;
  private GemView gemView;

  @Before
  public void setUp() throws Exception {
    gemView = mock(GemView.class);
    presenter = new GemPresenter(gemView);
  }

  @Test
  public void shouldOpenExternalLink() throws Exception {
    presenter.openExternalLink("http://www.singhajit.com");
    verify(gemView).navigateToExternalLink("http://www.singhajit.com");
  }

  @Test
  public void shouldShareGem() throws Exception {
    GemViewModel viewModel = mock(GemViewModel.class);
    when(viewModel.getName()).thenReturn("apkToJava");
    when(viewModel.getInfo()).thenReturn("Convert android apk file to java code with single command!");

    presenter.shareGem(viewModel);

    verify(gemView).openSendApplicationChooser("Found \"apkToJava\" gem while browsing through RubyGems android app.\n" +
        "#RubyGemsAndroidApp: goo.gl/Bxz2D1 \n" +
        "\n" +
        "Gem: https://rubygems.org/gems/apkToJava \n" +
        "Details: Convert android apk file to java code with single command!\n");
  }
}