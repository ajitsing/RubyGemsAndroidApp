package com.singhajit.rubygems.profile;

import com.singhajit.rubygems.trending.model.Gem;

import java.util.ArrayList;

public interface ProfileView {
  void showError(String message);

  void render(ArrayList<Gem> gems);
}
