package com.singhajit.rubygems.trending.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Dependencies implements Parcelable {
  private List<Dependency> development;
  private List<Dependency> runtime;

  protected Dependencies(Parcel in) {
    development = in.createTypedArrayList(Dependency.CREATOR);
    runtime = in.createTypedArrayList(Dependency.CREATOR);
  }

  public static final Creator<Dependencies> CREATOR = new Creator<Dependencies>() {
    @Override
    public Dependencies createFromParcel(Parcel in) {
      return new Dependencies(in);
    }

    @Override
    public Dependencies[] newArray(int size) {
      return new Dependencies[size];
    }
  };

  public List<Dependency> getDevelopment() {
    return development;
  }

  public List<Dependency> getRuntime() {
    return runtime;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel parcel, int i) {
    parcel.writeTypedList(development);
    parcel.writeTypedList(runtime);
  }
}
