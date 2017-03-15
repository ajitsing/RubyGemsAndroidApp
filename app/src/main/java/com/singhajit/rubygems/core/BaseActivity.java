package com.singhajit.rubygems.core;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.singhajit.rubygems.R;
import com.singhajit.rubygems.helpandfeedback.HelpAndFeedbackActivity;

public class BaseActivity extends AppCompatActivity {

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      onBackPressed();
      return true;
    } else if (item.getItemId() == R.id.help_and_feedback) {
      startActivity(new Intent(this, HelpAndFeedbackActivity.class));
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
