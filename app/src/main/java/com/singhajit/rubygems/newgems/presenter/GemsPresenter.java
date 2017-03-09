package com.singhajit.rubygems.newgems.presenter;

import android.support.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.singhajit.rubygems.core.APIClient;
import com.singhajit.rubygems.trending.model.Gem;
import com.singhajit.rubygems.trending.view.GemsView;

import java.util.ArrayList;
import java.util.List;

public abstract class GemsPresenter {
  private final APIClient apiClient;
  private final GemsView view;

  public GemsPresenter(APIClient apiClient, GemsView view) {
    this.apiClient = apiClient;
    this.view = view;
  }

  public void render() {
    StringRequest request = new StringRequest(Request.Method.GET, getGemsUrl(), onSuccess(), onError());
    view.showLoader();
    apiClient.makeRequest(request);
  }

  @NonNull
  public abstract String getGemsUrl();

  @NonNull
  private Response.ErrorListener onError() {
    return new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {
        view.hideLoader();
        view.notify(error.getMessage());
      }
    };
  }

  @NonNull
  private Response.Listener<String> onSuccess() {
    return new Response.Listener<String>() {
      @Override
      public void onResponse(String response) {
        ArrayList<Gem> gems = new Gson().fromJson(response, new TypeToken<List<Gem>>() {
        }.getType());
        view.hideLoader();
        view.render(gems);
      }
    };
  }

}
