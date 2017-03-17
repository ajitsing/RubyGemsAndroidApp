package com.singhajit.rubygems.gemdetails.viewmodel;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class EmbeddedWebViewModelTest {

  @Test
  public void shouldExtractDomainNameFromUrl() throws Exception {
    EmbeddedWebViewModel viewModel = new EmbeddedWebViewModel("https://www.singhajit.com/android-articles/rubygemsandroidapp");
    assertThat(viewModel.getDomainName(), is("https://www.singhajit.com"));
  }

  @Test
  public void shouldReturnOriginalUrlWhenUrlIsNotParsable() throws Exception {
    EmbeddedWebViewModel viewModel = new EmbeddedWebViewModel("httpfjskl://abc.somthing");
    assertThat(viewModel.getDomainName(), is("httpfjskl://abc.somthing"));
  }
}