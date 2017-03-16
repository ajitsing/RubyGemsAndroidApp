package com.singhajit.rubygems.core;

import com.singhajit.rubygems.network.BaseRequest;
import com.singhajit.rubygems.network.RubyGemsVolley;

public class NetworkActivity extends BaseActivity implements APIClient {
  public void makeRequest(BaseRequest request) {
    RubyGemsVolley.getInstance(this).addToRequestQueue(request);
  }

  @Override
  public void haltRequest(String url) {
    RubyGemsVolley.getInstance(this).getRequestQueue().cancelAll(url);
  }
}
