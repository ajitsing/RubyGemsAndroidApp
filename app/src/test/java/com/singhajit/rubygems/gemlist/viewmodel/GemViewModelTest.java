package com.singhajit.rubygems.gemlist.viewmodel;

import android.view.View;

import com.singhajit.rubygems.R;
import com.singhajit.rubygems.core.StringResolver;
import com.singhajit.rubygems.gemdetails.viewmodel.DependencyViewModel;
import com.singhajit.rubygems.recent.model.Dependency;
import com.singhajit.rubygems.recent.model.Gem;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GemViewModelTest {


  private GemViewModel gemViewModel;
  private Gem gem;
  private StringResolver stringResolver;

  @Before
  public void setUp() throws Exception {
    gem = mock(Gem.class, RETURNS_DEEP_STUBS);
    stringResolver = mock(StringResolver.class);
    gemViewModel = new GemViewModel(gem, stringResolver);
  }

  @Test
  public void shouldReturnCommaSeparatedNumberOfDownloads() throws Exception {
    when(gem.getDownloads()).thenReturn(10000D);
    assertThat(gemViewModel.getDownloads(), is("10,000"));
  }

  @Test
  public void shouldReturnGemName() throws Exception {
    when(gem.getName()).thenReturn("apkToJava");
    assertThat(gemViewModel.getName(), is("apkToJava"));
  }

  @Test
  public void shouldReturnGemVersion() throws Exception {
    when(gem.getVersion()).thenReturn("1.2.1");
    assertThat(gemViewModel.getVersion(), is("1.2.1"));
  }

  @Test
  public void shouldReturnCommaSeparatedNumberOfCurrentVersionDownloads() throws Exception {
    when(gem.getVersionDownloads()).thenReturn(1000D);
    assertThat(gemViewModel.getVersionDownloads(), is("1,000"));
  }

  @Test
  public void shouldReturnRuntimeDependenciesHeadingText() throws Exception {
    int numberOfDependencies = 2;
    when(stringResolver.getString(R.string.runtime_dependencies, numberOfDependencies)).thenReturn("RUNTIME DEPENDENCIES (" + numberOfDependencies + ")");
    when(gem.getDependencies().getRuntime().size()).thenReturn(numberOfDependencies);

    assertThat(gemViewModel.getRuntimeDependenciesText(), is("RUNTIME DEPENDENCIES (2)"));
  }

  @Test
  public void shouldReturnDevelopmentDependenciesHeadingText() throws Exception {
    int numberOfDependencies = 2;
    when(stringResolver.getString(R.string.development_dependencies, numberOfDependencies)).thenReturn("DEVELOPMENT DEPENDENCIES (" + numberOfDependencies + ")");
    when(gem.getDependencies().getDevelopment().size()).thenReturn(numberOfDependencies);

    assertThat(gemViewModel.getDevelopmentDependenciesText(), is("DEVELOPMENT DEPENDENCIES (2)"));
  }

  @Test
  public void shouldReturnDevelopmentDependenciesVisibility() throws Exception {
    when(gem.getDependencies().hasDevDependencies()).thenReturn(false);
    assertThat(gemViewModel.getDevelopmentDependencyVisibility().getVisibility(), is(View.GONE));

    when(gem.getDependencies().hasDevDependencies()).thenReturn(true);
    assertThat(gemViewModel.getDevelopmentDependencyVisibility().getVisibility(), is(View.VISIBLE));
  }

  @Test
  public void shouldReturnRuntimeDependenciesVisibility() throws Exception {
    when(gem.getDependencies().hasRuntimeDependencies()).thenReturn(false);
    assertThat(gemViewModel.getRuntimeDependencyVisibility().getVisibility(), is(View.GONE));

    when(gem.getDependencies().hasRuntimeDependencies()).thenReturn(true);
    assertThat(gemViewModel.getRuntimeDependencyVisibility().getVisibility(), is(View.VISIBLE));
  }

  @Test
  public void shouldReturnAuthorsVisibility() throws Exception {
    when(gem.hasAuthors()).thenReturn(false);
    assertThat(gemViewModel.getAuthorsVisibility().getVisibility(), is(View.GONE));

    when(gem.hasAuthors()).thenReturn(true);
    assertThat(gemViewModel.getAuthorsVisibility().getVisibility(), is(View.VISIBLE));
  }

  @Test
  public void shouldReturnNAWhenLicensesAreNotPresent() throws Exception {
    when(gem.hasLicenses()).thenReturn(false);
    when(stringResolver.getString(R.string.not_available)).thenReturn("N/A");

    assertThat(gemViewModel.getLicenses(), is("N/A"));
  }

  @Test
  public void shouldReturnAuthors() throws Exception {
    when(gem.getAuthors()).thenReturn("Ajit Singh");
    assertThat(gemViewModel.getAuthors(), is("Ajit Singh"));
  }

  @Test
  public void shouldReturnGemInfo() throws Exception {
    when(gem.getInfo()).thenReturn("Some info");
    assertThat(gemViewModel.getInfo(), is("Some info"));
  }

  @Test
  public void shouldGetRuntimeDependenciesViewModels() throws Exception {
    Dependency dependency1 = mock(Dependency.class);
    when(dependency1.getName()).thenReturn("active_support");
    Dependency dependency2 = mock(Dependency.class);
    when(dependency2.getName()).thenReturn("pry");
    when(gem.getDependencies().getRuntime()).thenReturn(asList(dependency1, dependency2));

    List<DependencyViewModel> runtimeDependencyViewModels = gemViewModel.getRuntimeDependencyViewModels();

    assertThat(runtimeDependencyViewModels.size(), is(2));
    assertThat(runtimeDependencyViewModels.get(0).getName(), is("active_support"));
    assertThat(runtimeDependencyViewModels.get(1).getName(), is("pry"));
  }

  @Test
  public void shouldGetDevelopmentDependenciesViewModels() throws Exception {
    Dependency dependency1 = mock(Dependency.class);
    when(dependency1.getName()).thenReturn("rake");
    Dependency dependency2 = mock(Dependency.class);
    when(dependency2.getName()).thenReturn("rspec");
    when(gem.getDependencies().getDevelopment()).thenReturn(asList(dependency1, dependency2));

    List<DependencyViewModel> developmentDependencyViewModels = gemViewModel.getDevelopmentDependencyViewModels();

    assertThat(developmentDependencyViewModels.size(), is(2));
    assertThat(developmentDependencyViewModels.get(0).getName(), is("rake"));
    assertThat(developmentDependencyViewModels.get(1).getName(), is("rspec"));
  }
}