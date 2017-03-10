package com.singhajit.rubygems.core;

import com.singhajit.rubygems.network.BaseRequest;

public interface APIClient {
  void makeRequest(BaseRequest request);
}
