package com.singhajit.rubygems.gemdetails.viewmodel;

import com.singhajit.rubygems.recent.model.Dependency;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DependencyViewModelTest {

  @Test
  public void shouldReturnViewModelDetails() throws Exception {
    Dependency dependency = mock(Dependency.class);
    when(dependency.getName()).thenReturn("fancy_audio");
    when(dependency.getRequirements()).thenReturn("None");

    DependencyViewModel viewModel = new DependencyViewModel(dependency);

    assertThat(viewModel.getName(), is("fancy_audio"));
    assertThat(viewModel.getRequirements(), is("None"));
  }
}