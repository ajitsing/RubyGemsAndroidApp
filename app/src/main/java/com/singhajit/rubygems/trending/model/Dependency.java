package com.singhajit.rubygems.trending.model;

import android.os.Parcel;

public class Dependency {
  private String name;
  private String requirements;

  protected Dependency(Parcel in) {
    name = in.readString();
    requirements = in.readString();
  }

  public String getName() {
    return name;
  }

  public String getRequirements() {
    return requirements;
  }
}
