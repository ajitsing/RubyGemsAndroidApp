package com.singhajit.rubygems.profile;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.singhajit.rubygems.R;
import com.singhajit.rubygems.core.APIClient;
import com.singhajit.rubygems.core.SharedPrefRepo;
import com.singhajit.rubygems.databinding.ProfileBinding;
import com.singhajit.rubygems.gemlist.GemListRenderer;
import com.singhajit.rubygems.profile.presenter.LoginPresenter;
import com.singhajit.rubygems.profile.viewmodel.LoginViewModel;
import com.singhajit.rubygems.profile.viewmodel.ProfileViewModel;
import com.singhajit.rubygems.trending.model.Gem;

import java.util.ArrayList;

import static com.singhajit.rubygems.profile.presenter.LoginPresenter.USERNAME;

public class UserProfileFragment extends Fragment implements ProfileView {

  private ProfileBinding binding;
  private SharedPrefRepo sharedPrefRepo;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    binding = DataBindingUtil.inflate(inflater, R.layout.profile_fragment, container, false);
    LoginViewModel loginViewModel = new LoginViewModel();
    binding.setLoginViewModel(loginViewModel);
    sharedPrefRepo = new SharedPrefRepo(getActivity());
    LoginPresenter presenter = new LoginPresenter((APIClient) getActivity(), sharedPrefRepo, this);
    binding.setPresenter(presenter);
    presenter.fetchUserGems(loginViewModel);
    return binding.getRoot();
  }

  @Override
  public void showError(String message) {
    Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_LONG).show();
  }

  @Override
  public void render(ArrayList<Gem> gems) {
    binding.setProfileViewModel(new ProfileViewModel(gems, sharedPrefRepo.get(USERNAME)));
    GemListRenderer gemListRenderer = new GemListRenderer(gems, binding.userGemsList);
    gemListRenderer.render();
  }
}
