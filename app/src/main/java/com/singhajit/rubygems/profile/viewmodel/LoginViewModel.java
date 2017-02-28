package com.singhajit.rubygems.profile.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.singhajit.rubygems.BR;
import com.singhajit.rubygems.core.ViewVisibility;

public class LoginViewModel extends BaseObservable {
  private String username;
  private String password;
  private ViewVisibility loginFormVisibility = new ViewVisibility(true);
  private ViewVisibility loaderVisibility = new ViewVisibility(false);

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Bindable
  public ViewVisibility getLoginFormVisibility() {
    return loginFormVisibility;
  }

  public void setLoginFormVisibility(boolean isVisible) {
    this.loginFormVisibility = new ViewVisibility(isVisible);
    notifyPropertyChanged(BR.loginFormVisibility);
  }

  @Bindable
  public ViewVisibility getLoaderVisibility() {
    return loaderVisibility;
  }

  public void setLoaderVisibility(boolean isVisible) {
    this.loaderVisibility = new ViewVisibility(isVisible);
    notifyPropertyChanged(BR.loaderVisibility);
  }
}
