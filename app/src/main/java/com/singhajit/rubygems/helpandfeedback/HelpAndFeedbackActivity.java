package com.singhajit.rubygems.helpandfeedback;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.singhajit.rubygems.R;
import com.singhajit.rubygems.core.BaseActivity;
import com.singhajit.rubygems.databinding.HelpAndFeedbackBinding;
import com.singhajit.rubygems.helpandfeedback.presenter.HelpAndFeedbackPresenter;
import com.singhajit.rubygems.helpandfeedback.viewmodel.HelpAndFeedbackViewModel;

import static android.content.Intent.ACTION_VIEW;

public class HelpAndFeedbackActivity extends BaseActivity implements WebsiteNavigator {
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    HelpAndFeedbackBinding binding = DataBindingUtil.setContentView(this, R.layout.help_and_feedback_activity);
    binding.setViewModel(new HelpAndFeedbackViewModel());
    binding.setPresenter(new HelpAndFeedbackPresenter(this));

    setSupportActionBar(binding.toolbar);
    getSupportActionBar().setHomeButtonEnabled(true);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    setTitle(getString(R.string.help_and_feedback));
  }

  @Override
  public void openInBrowser(String url) {
    Intent intent = new Intent(ACTION_VIEW, Uri.parse(url));
    startActivity(intent);
  }
}
