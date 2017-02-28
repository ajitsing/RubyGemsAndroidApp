package com.singhajit.rubygems.profile.model;

import com.google.gson.annotations.SerializedName;

public class RubyGemsAPIKey {
  @SerializedName("rubygems_api_key")
  private String key;

  public String getKey() {
    return key;
  }
}
