package com.singhajit.rubygems.search.presenter;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.singhajit.rubygems.core.APIClient;
import com.singhajit.rubygems.network.BaseRequest;
import com.singhajit.rubygems.search.GemSearchView;
import com.singhajit.rubygems.search.viewmodel.GemSearchViewModel;
import com.singhajit.rubygems.trending.model.Gem;

import java.util.ArrayList;

import static com.singhajit.rubygems.core.RubyGemsAPIs.SEARCH_GEM;

public class GemSearchPresenter {
  private final APIClient apiClient;
  private final GemSearchView view;

  public GemSearchPresenter(APIClient apiClient, GemSearchView view) {
    this.apiClient = apiClient;
    this.view = view;
  }

  public void onSearch(GemSearchViewModel viewModel) {
    viewModel.setGemsVisibility(false);
    viewModel.setLoaderVisibility(true);
    apiClient.makeRequest(new BaseRequest(Request.Method.GET, SEARCH_GEM.replace("%s", viewModel.getSearchString()), onSearchSuccess(viewModel), onFailure(viewModel)));
  }

  private Response.ErrorListener onFailure(final GemSearchViewModel viewModel) {
    return new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {
        viewModel.setLoaderVisibility(false);
        view.showError(error.getMessage());
      }
    };
  }

  private Response.Listener<String> onSearchSuccess(final GemSearchViewModel viewModel) {
    return new Response.Listener<String>() {
      @Override
      public void onResponse(String response) {
        ArrayList<Gem> searchResults = new Gson().fromJson(response, new TypeToken<ArrayList<Gem>>() {
        }.getType());
        viewModel.setLoaderVisibility(false);
        viewModel.setGemsVisibility(true);
        view.renderResults(searchResults);
      }
    };
  }
}
