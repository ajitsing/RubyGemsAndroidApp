package com.singhajit.rubygems.core;

import com.android.volley.toolbox.StringRequest;

public interface APIClient {
  void makeRequest(StringRequest request);
}
