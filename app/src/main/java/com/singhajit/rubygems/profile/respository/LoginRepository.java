package com.singhajit.rubygems.profile.respository;

import com.singhajit.rubygems.core.SharedPrefRepo;
import com.singhajit.rubygems.profile.viewmodel.LoginViewModel;

import static com.singhajit.rubygems.core.SharedPrefRepo.API_KEY;
import static com.singhajit.rubygems.core.SharedPrefRepo.NAME;
import static com.singhajit.rubygems.core.SharedPrefRepo.USERNAME;

public class LoginRepository {
  private final SharedPrefRepo sharedPrefRepo;

  public LoginRepository(SharedPrefRepo sharedPrefRepo) {
    this.sharedPrefRepo = sharedPrefRepo;
  }

  public void login(String key, LoginViewModel viewModel) {
    sharedPrefRepo.put(API_KEY, key);
    sharedPrefRepo.put(USERNAME, viewModel.getUsername());
    sharedPrefRepo.put(NAME, viewModel.getName());
  }

  public void logout() {
    sharedPrefRepo.remove(API_KEY);
  }
}
