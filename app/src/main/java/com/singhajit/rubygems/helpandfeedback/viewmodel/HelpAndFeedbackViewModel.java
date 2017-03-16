package com.singhajit.rubygems.helpandfeedback.viewmodel;

import android.support.annotation.StringRes;

import com.singhajit.rubygems.R;

public class HelpAndFeedbackViewModel {
  @StringRes
  public int getGithubLink() {
    return R.string.github_url;
  }

  @StringRes
  public int getWebsiteLink() {
    return R.string.website_url;
  }
}
