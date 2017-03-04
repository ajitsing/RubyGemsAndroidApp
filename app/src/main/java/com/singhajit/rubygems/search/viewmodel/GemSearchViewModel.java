package com.singhajit.rubygems.search.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.singhajit.rubygems.BR;
import com.singhajit.rubygems.core.ViewVisibility;

public class GemSearchViewModel extends BaseObservable {
  private ViewVisibility loaderVisibility = new ViewVisibility(false);
  private ViewVisibility gemsVisibility = new ViewVisibility(false);
  private String searchString = "";

  @Bindable
  public String getSearchString() {
    return searchString;
  }

  public void setSearchString(String searchString) {
    this.searchString = searchString;
    notifyPropertyChanged(BR.clearTextVisibility);
  }

  @Bindable
  public ViewVisibility getLoaderVisibility() {
    return loaderVisibility;
  }

  @Bindable
  public ViewVisibility getGemsVisibility() {
    return gemsVisibility;
  }

  @Bindable
  public ViewVisibility getClearTextVisibility() {
    return new ViewVisibility(!searchString.isEmpty());
  }

  public void clearSearchString() {
    searchString = "";
    notifyPropertyChanged(BR.searchString);
  }

  public void setGemsVisibility(boolean isVisible) {
    this.gemsVisibility = new ViewVisibility(isVisible);
    notifyPropertyChanged(BR.gemsVisibility);
  }

  public void setLoaderVisibility(boolean isVisible) {
    this.loaderVisibility = new ViewVisibility(isVisible);
    notifyPropertyChanged(BR.loaderVisibility);
  }
}
