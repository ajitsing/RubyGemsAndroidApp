package com.singhajit.rubygems.core;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class NetworkActivity extends BaseActivity implements APIClient {

  private RequestQueue requestQueue;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestQueue = Volley.newRequestQueue(this);
    requestQueue.start();
  }

  @Override
  protected void onStop() {
    super.onStop();
    requestQueue.stop();
  }

  public void makeRequest(StringRequest request) {
    requestQueue.add(request);
  }
}
