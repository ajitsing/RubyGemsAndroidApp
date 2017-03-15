package com.singhajit.rubygems.helpandfeedback;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.singhajit.rubygems.R;
import com.singhajit.rubygems.core.BaseActivity;
import com.singhajit.rubygems.databinding.HelpAndFeedbackBinding;

public class HelpAndFeedbackActivity extends BaseActivity {
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    HelpAndFeedbackBinding binding = DataBindingUtil.setContentView(this, R.layout.help_and_feedback_activity);

    setSupportActionBar(binding.toolbar);
    getSupportActionBar().setHomeButtonEnabled(true);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    setTitle(getString(R.string.help_and_feedback));
  }
}
