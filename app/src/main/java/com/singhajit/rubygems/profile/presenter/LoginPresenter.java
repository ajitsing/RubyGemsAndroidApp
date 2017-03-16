package com.singhajit.rubygems.profile.presenter;

import android.support.annotation.NonNull;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.singhajit.rubygems.core.APIClient;
import com.singhajit.rubygems.core.SharedPrefRepo;
import com.singhajit.rubygems.network.BaseRequest;
import com.singhajit.rubygems.profile.ProfileView;
import com.singhajit.rubygems.profile.model.RubyGemsAPIKey;
import com.singhajit.rubygems.profile.request.CachedUserGemsRequest;
import com.singhajit.rubygems.profile.request.LoginRequest;
import com.singhajit.rubygems.profile.request.UserGemsRequest;
import com.singhajit.rubygems.profile.respository.LoginRepository;
import com.singhajit.rubygems.profile.viewmodel.LoginViewModel;
import com.singhajit.rubygems.recent.model.Gem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static com.singhajit.rubygems.core.SharedPrefRepo.API_KEY;

public class LoginPresenter {
  private final APIClient apiClient;
  private final SharedPrefRepo sharedPrefRepo;
  private final ProfileView view;
  private final LoginRepository loginRespository;

  public LoginPresenter(APIClient apiClient, SharedPrefRepo sharedPrefRepo, ProfileView view) {
    this.apiClient = apiClient;
    this.sharedPrefRepo = sharedPrefRepo;
    this.view = view;
    this.loginRespository = new LoginRepository(sharedPrefRepo);
  }

  public void login(final LoginViewModel viewModel) {
    viewModel.setLoaderVisibility(true);
    LoginRequest request = new LoginRequest(viewModel.getUsername(), viewModel.getPassword(), onSuccessFullLogin(viewModel), onLoginError(viewModel));
    viewModel.setLoginFormVisibility(false);
    apiClient.makeRequest(request);
  }

  public void fetchUserGems(final LoginViewModel viewModel) {
    final String apiKey = sharedPrefRepo.get(API_KEY);
    if (apiKey != null) {
      viewModel.setLoginFormVisibility(false);
      viewModel.setLoaderVisibility(true);
      getUserGems(viewModel, true);
    }
  }

  public void logout(LoginViewModel viewModel) {
    loginRespository.logout();
    viewModel.setProfileCardVisibility(false);
    viewModel.setLoginFormVisibility(true);
  }

  public void refresh(final LoginViewModel viewModel) {
    final String apiKey = sharedPrefRepo.get(API_KEY);
    if (apiKey != null) {
      view.showPullToRefreshLoader();
      getUserGems(viewModel, false);
    }
  }

  public void renderSavedState(ArrayList<Gem> gems, LoginViewModel loginViewModel) {
    loginViewModel.setLoginFormVisibility(false);
    loginViewModel.setProfileCardVisibility(true);
    view.render(gems);
  }

  private void getUserGems(final LoginViewModel viewModel, boolean enableCache) {
    BaseRequest request;
    if (enableCache) {
      request = new CachedUserGemsRequest(sharedPrefRepo.get(API_KEY), onCachedUserGemsSuccess(viewModel), onUserGemsFetchError(viewModel));
    } else {
      request = new UserGemsRequest(sharedPrefRepo.get(API_KEY), onUserGemsFetchSuccess(viewModel), onUserGemsFetchError(viewModel));
    }
    apiClient.makeRequest(request);
  }

  @NonNull
  private Response.Listener<String> onUserGemsFetchSuccess(final LoginViewModel viewModel) {
    return new Response.Listener<String>() {
      @Override
      public void onResponse(String response) {
        ArrayList<Gem> gems = new Gson().fromJson(response, new TypeToken<ArrayList<Gem>>() {
        }.getType());
        view.render(sortGems(gems));
        viewModel.setLoaderVisibility(false);
        viewModel.setProfileCardVisibility(true);
      }
    };
  }

  @NonNull
  private Response.Listener<String> onCachedUserGemsSuccess(final LoginViewModel viewModel) {
    return new Response.Listener<String>() {
      @Override
      public void onResponse(String response) {
        onUserGemsFetchSuccess(viewModel).onResponse(response);
        refresh(viewModel);
      }
    };
  }

  private ArrayList<Gem> sortGems(ArrayList<Gem> gems) {
    Collections.sort(gems, new Comparator<Gem>() {
      @Override
      public int compare(Gem gem1, Gem gem2) {
        return gem2.getDownloads().compareTo(gem1.getDownloads());
      }
    });
    return gems;
  }

  @NonNull
  private Response.Listener<String> onSuccessFullLogin(final LoginViewModel viewModel) {
    return new Response.Listener<String>() {
      @Override
      public void onResponse(String response) {
        RubyGemsAPIKey rubyGemsAPIKey = new Gson().fromJson(response, RubyGemsAPIKey.class);
        loginRespository.login(rubyGemsAPIKey.getKey(), viewModel);
        getUserGems(viewModel, true);
      }
    };
  }

  @NonNull
  private Response.ErrorListener onLoginError(final LoginViewModel viewModel) {
    return new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {
        viewModel.setLoaderVisibility(false);
        viewModel.setLoginFormVisibility(true);
        view.showError(error.getMessage());
      }
    };
  }

  @NonNull
  private Response.ErrorListener onUserGemsFetchError(final LoginViewModel viewModel) {
    return new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {
        viewModel.setLoaderVisibility(false);
        view.showError(error.getMessage());
      }
    };
  }
}
