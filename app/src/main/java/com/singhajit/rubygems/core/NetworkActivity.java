package com.singhajit.rubygems.core;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class NetworkActivity extends AppCompatActivity implements APIClient {

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

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      onBackPressed();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
