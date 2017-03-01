package com.singhajit.rubygems.trending.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Dependency implements Parcelable {
  private String name;
  private String requirements;

  protected Dependency(Parcel in) {
    name = in.readString();
    requirements = in.readString();
  }

  public static final Creator<Dependency> CREATOR = new Creator<Dependency>() {
    @Override
    public Dependency createFromParcel(Parcel in) {
      return new Dependency(in);
    }

    @Override
    public Dependency[] newArray(int size) {
      return new Dependency[size];
    }
  };

  public String getName() {
    return name;
  }

  public String getRequirements() {
    return requirements;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel parcel, int i) {
    parcel.writeString(name);
    parcel.writeString(requirements);
  }
}
