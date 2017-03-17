package com.singhajit.rubygems.helpandfeedback.viewmodel;

import com.singhajit.rubygems.R;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class HelpAndFeedbackViewModelTest {

  @Test
  public void shouldGetGithubLink() throws Exception {
    assertThat(new HelpAndFeedbackViewModel().getGithubLink(), is(R.string.github_url));
  }

  @Test
  public void shouldGetWebsiteLink() throws Exception {
    assertThat(new HelpAndFeedbackViewModel().getWebsiteLink(), is(R.string.website_url));
  }
}