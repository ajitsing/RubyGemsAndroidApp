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
import com.singhajit.rubygems.profile.presenter.LoginPresenter;
import com.singhajit.rubygems.profile.viewmodel.LoginViewModel;

public class UserProfileFragment extends Fragment implements ProfileView {

  private ProfileBinding binding;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    binding = DataBindingUtil.inflate(inflater, R.layout.profile_fragment, container, false);
    binding.setLoginViewModel(new LoginViewModel());
    binding.setPresenter(new LoginPresenter((APIClient) getActivity(), new SharedPrefRepo(getActivity()), this));
    return binding.getRoot();
  }

  @Override
  public void showError(String message) {
    Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_LONG).show();
  }
}
