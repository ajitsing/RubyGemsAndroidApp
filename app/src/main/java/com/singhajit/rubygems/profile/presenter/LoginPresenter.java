package com.singhajit.rubygems.profile.presenter;

import android.support.annotation.NonNull;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.singhajit.rubygems.core.APIClient;
import com.singhajit.rubygems.core.SharedPrefRepo;
import com.singhajit.rubygems.profile.ProfileView;
import com.singhajit.rubygems.profile.model.RubyGemsAPIKey;
import com.singhajit.rubygems.profile.request.LoginRequest;
import com.singhajit.rubygems.profile.request.UserGemsRequest;
import com.singhajit.rubygems.profile.viewmodel.LoginViewModel;
import com.singhajit.rubygems.trending.model.Gem;

import java.util.ArrayList;

public class LoginPresenter {
  private final APIClient apiClient;
  private final SharedPrefRepo sharedPrefRepo;
  private final ProfileView view;
  private final String API_KEY = "API_KEY";

  public LoginPresenter(APIClient apiClient, SharedPrefRepo sharedPrefRepo, ProfileView view) {
    this.apiClient = apiClient;
    this.sharedPrefRepo = sharedPrefRepo;
    this.view = view;
  }

  public void login(final LoginViewModel viewModel) {
    viewModel.setLoaderVisibility(true);
    StringRequest request = new LoginRequest(viewModel.getUsername(), viewModel.getPassword(), onSuccess(viewModel), onError(viewModel));
    viewModel.setLoginFormVisibility(false);
    apiClient.makeRequest(request);
  }

  public void fetchUserGems(final LoginViewModel viewModel) {
    final String apiKey = sharedPrefRepo.get(API_KEY);
    if (apiKey != null) {
      viewModel.setLoginFormVisibility(false);
      viewModel.setLoaderVisibility(true);
      getUserGems(viewModel);
    }
  }

  private void getUserGems(final LoginViewModel viewModel) {
    UserGemsRequest userGemsRequest = new UserGemsRequest(sharedPrefRepo.get(API_KEY), new Response.Listener<String>() {
      @Override
      public void onResponse(String response) {
        ArrayList<Gem> gems = new Gson().fromJson(response, new TypeToken<ArrayList<Gem>>() {
        }.getType());
        view.render(gems);
        viewModel.setLoaderVisibility(false);
      }
    }, onError(viewModel));
    apiClient.makeRequest(userGemsRequest);
  }

  @NonNull
  private Response.Listener<String> onSuccess(final LoginViewModel viewModel) {
    return new Response.Listener<String>() {
      @Override
      public void onResponse(String response) {
        RubyGemsAPIKey rubyGemsAPIKey = new Gson().fromJson(response, RubyGemsAPIKey.class);
        sharedPrefRepo.put(API_KEY, rubyGemsAPIKey.getKey());
        getUserGems(viewModel);
      }
    };
  }

  @NonNull
  private Response.ErrorListener onError(final LoginViewModel viewModel) {
    return new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {
        viewModel.setLoaderVisibility(false);
        viewModel.setLoginFormVisibility(true);
        view.showError(error.getMessage());
      }
    };
  }
}
