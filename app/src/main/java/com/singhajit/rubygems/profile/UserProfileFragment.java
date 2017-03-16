package com.singhajit.rubygems.profile;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.singhajit.rubygems.R;
import com.singhajit.rubygems.core.APIClient;
import com.singhajit.rubygems.core.ErrorHandler;
import com.singhajit.rubygems.core.SharedPrefRepo;
import com.singhajit.rubygems.databinding.ProfileBinding;
import com.singhajit.rubygems.gemlist.GemListRenderer;
import com.singhajit.rubygems.profile.presenter.LoginPresenter;
import com.singhajit.rubygems.profile.viewmodel.LoginViewModel;
import com.singhajit.rubygems.profile.viewmodel.ProfileViewModel;
import com.singhajit.rubygems.recent.model.Gem;

import java.util.ArrayList;

import static com.singhajit.rubygems.core.SharedPrefRepo.NAME;
import static com.singhajit.rubygems.core.SharedPrefRepo.USERNAME;
import static com.singhajit.rubygems.recent.view.RecentlyUpdatedGemsFragment.GEM_LIST;

public class UserProfileFragment extends Fragment implements ProfileView {

  private ProfileBinding binding;
  private SharedPrefRepo sharedPrefRepo;
  private ArrayList<Gem> gems = new ArrayList<>();
  private LoginPresenter presenter;
  private LoginViewModel loginViewModel;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    binding = DataBindingUtil.inflate(inflater, R.layout.profile_fragment, container, false);
    sharedPrefRepo = new SharedPrefRepo(getActivity());
    loginViewModel = new LoginViewModel(sharedPrefRepo.get(NAME), sharedPrefRepo.get(USERNAME));
    binding.setLoginViewModel(loginViewModel);
    presenter = new LoginPresenter((APIClient) getActivity(), sharedPrefRepo, this);
    binding.setPresenter(presenter);

    if (savedInstanceState != null && !savedInstanceState.<Gem>getParcelableArrayList(GEM_LIST).isEmpty()) {
      presenter.renderSavedState(savedInstanceState.<Gem>getParcelableArrayList(GEM_LIST), loginViewModel);
    } else {
      presenter.fetchUserGems(loginViewModel);
    }
    return binding.getRoot();
  }

  @Override
  public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putParcelableArrayList(GEM_LIST, gems);
  }

  @Override
  public void showError(String message) {
    binding.refreshLayout.setRefreshing(false);
    ErrorHandler.showSnackBar(binding.getRoot(), message, new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        presenter.fetchUserGems(loginViewModel);
      }
    });
  }

  @Override
  public void render(ArrayList<Gem> gems) {
    boolean isRefresh = !this.gems.isEmpty();
    this.gems = gems;
    binding.setProfileViewModel(new ProfileViewModel(gems, sharedPrefRepo.get(USERNAME), sharedPrefRepo.get(NAME)));
    GemListRenderer gemListRenderer = new GemListRenderer(gems, binding.userGemsList);
    gemListRenderer.render(isRefresh);
    binding.refreshLayout.setRefreshing(false);
  }

  @Override
  public void showPullToRefreshLoader() {
    binding.refreshLayout.setRefreshing(true);
  }
}
