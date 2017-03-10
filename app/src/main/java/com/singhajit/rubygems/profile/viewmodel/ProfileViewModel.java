package com.singhajit.rubygems.profile.viewmodel;

import com.singhajit.rubygems.recent.model.Gem;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProfileViewModel {
  private final String username;
  private List<Gem> gems = new ArrayList<>();

  public ProfileViewModel(List<Gem> gems, String username) {
    this.username = username;
    this.gems = gems;
  }

  public String getProfileImageUrl() {
    String hash = null;
    String defaultImageUrl = "http://www.singhajit.com/wp-content/uploads/2015/06/android-e1435285902595.png";

    if (!isEmail(username)) {
      return defaultImageUrl;
    }
    try {
      MessageDigest md5 = MessageDigest.getInstance("md5");
      byte[] digest = md5.digest(username.getBytes("UTF-8"));
      BigInteger bigInt = new BigInteger(1, digest);
      hash = bigInt.toString(16);
      while (hash.length() < 32) {
        hash = "0" + hash;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return hash != null ? "https://www.gravatar.com/avatar/" + hash : defaultImageUrl;
  }

  public String getUsername() {
    if (isEmail(username)) {
      String[] split = username.split("@");
      return split[0];
    }
    return username;
  }

  public String getTotalDownloads() {
    Double totalDownloads = 0D;
    for (Gem gem : gems) {
      totalDownloads = totalDownloads + gem.getDownloads();
    }
    return NumberFormat.getInstance().format(totalDownloads);
  }

  public String getTotalGems() {
    return NumberFormat.getInstance().format(gems.size());
  }

  private boolean isEmail(String email) {
    Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
    Matcher m = p.matcher(email);
    return m.matches();
  }
}
