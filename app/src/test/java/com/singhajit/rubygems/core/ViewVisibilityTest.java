package com.singhajit.rubygems.core;

import android.view.View;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ViewVisibilityTest {

  @Test
  public void shouldReturnViewVisibilityAsGONEWhenViewIsHidden() throws Exception {
    assertThat(new ViewVisibility(false).getVisibility(), is(View.GONE));
  }

  @Test
  public void shouldReturnViewVisibilityAsVISIBLEWhenViewIsShown() throws Exception {
    assertThat(new ViewVisibility(true).getVisibility(), is(View.VISIBLE));
  }
}