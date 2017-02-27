package com.singhajit.rubygems.trending.model;

import java.util.List;

public class Dependencies {
  private List<Dependency> development;
  private List<Dependency> runtime;

  public List<Dependency> getDevelopment() {
    return development;
  }

  public List<Dependency> getRuntime() {
    return runtime;
  }

}
