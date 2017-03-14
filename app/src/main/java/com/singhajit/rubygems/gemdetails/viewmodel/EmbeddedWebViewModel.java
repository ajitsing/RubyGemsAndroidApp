package com.singhajit.rubygems.gemdetails.viewmodel;

import android.util.Log;

import java.net.URI;
import java.net.URISyntaxException;

public class EmbeddedWebViewModel {
  private final String url;

  public EmbeddedWebViewModel(String url) {
    this.url = url;
  }

  public String getDomainName() {
    URI uri = null;
    try {
      uri = new URI(url);
    } catch (URISyntaxException e) {
      Log.d(EmbeddedWebViewModel.class.getSimpleName(), e.getMessage());
    }
    return (uri == null) ? url : uri.getScheme() + "://" + uri.getHost();
  }
}
