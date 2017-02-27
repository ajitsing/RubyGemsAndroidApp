package com.singhajit.rubygems.trending.presenter;

import android.support.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.singhajit.rubygems.core.APIClient;
import com.singhajit.rubygems.trending.model.Gem;
import com.singhajit.rubygems.trending.view.TrendingView;
import com.singhajit.rubygems.trending.viewmodel.TrendingViewModel;

import java.util.List;

import static com.singhajit.rubygems.core.RubyGemsAPIs.JUST_UPDATED;

public class TrendingPresenter {
  private final APIClient apiClient;
  private final TrendingView view;

  public TrendingPresenter(APIClient apiClient, TrendingView view) {
    this.apiClient = apiClient;
    this.view = view;
  }

  public void render() {
    StringRequest request = new StringRequest(Request.Method.GET, JUST_UPDATED, onSuccess(), onError());
    view.showLoader();
    apiClient.makeRequest(request);
  }

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
        List<Gem> gems = new Gson().fromJson(response, new TypeToken<List<Gem>>() {
        }.getType());
        view.hideLoader();
        view.render(new TrendingViewModel(gems));
      }
    };
  }
}