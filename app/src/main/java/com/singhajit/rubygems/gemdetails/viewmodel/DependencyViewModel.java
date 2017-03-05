package com.singhajit.rubygems.gemdetails.viewmodel;


import com.singhajit.rubygems.trending.model.Dependency;

public class DependencyViewModel {
  private final Dependency dependency;

  public DependencyViewModel(Dependency dependency) {
    this.dependency = dependency;
  }

  public String getName() {
    return dependency.getName();
  }

  public String getRequirements() {
    return dependency.getRequirements();
  }
}
