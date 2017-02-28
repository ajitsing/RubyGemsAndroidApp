package com.singhajit.rubygems.profile.presenter;

import android.support.annotation.NonNull;
import android.util.Base64;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.singhajit.rubygems.core.APIClient;
import com.singhajit.rubygems.core.RubyGemsAPIs;
import com.singhajit.rubygems.core.SharedPrefRepo;
import com.singhajit.rubygems.profile.ProfileView;
import com.singhajit.rubygems.profile.model.RubyGemsAPIKey;
import com.singhajit.rubygems.profile.viewmodel.LoginViewModel;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

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

    try {
      StringRequest request = getLoginRequest(viewModel);
      request.setShouldCache(false);
      viewModel.setLoginFormVisibility(false);
      apiClient.makeRequest(request);
    } catch (UnsupportedEncodingException e) {
      Log.e(LoginPresenter.class.getSimpleName(), e.getMessage());
      viewModel.setLoaderVisibility(false);
      viewModel.setLoginFormVisibility(true);
      view.showError("Something went wrong!");
    }
  }

  @NonNull
  private StringRequest getLoginRequest(final LoginViewModel viewModel) throws UnsupportedEncodingException {
    final String usernamePass = Base64.encodeToString((viewModel.getUsername() + ":" + viewModel.getPassword()).getBytes("UTF-8"), Base64.NO_WRAP);
    return new StringRequest(Request.Method.GET, RubyGemsAPIs.API_KEY, onSuccess(viewModel), onError(viewModel)) {
      @Override
      public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = super.getHeaders();
        Map<String, String> myHeaders = new HashMap<>();
        myHeaders.putAll(headers);
        myHeaders.put("Authorization", "Basic " + usernamePass);
        return myHeaders;
      }
    };
  }

  @NonNull
  private Response.Listener<String> onSuccess(final LoginViewModel viewModel) {
    return new Response.Listener<String>() {
      @Override
      public void onResponse(String response) {
        RubyGemsAPIKey rubyGemsAPIKey = new Gson().fromJson(response, RubyGemsAPIKey.class);
        sharedPrefRepo.put(API_KEY, rubyGemsAPIKey.getKey());
        viewModel.setLoaderVisibility(false);
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
