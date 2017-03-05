package com.singhajit.rubygems.gemlist.viewmodel;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.singhajit.rubygems.R;
import com.singhajit.rubygems.core.StringResolver;
import com.singhajit.rubygems.gemdetails.viewmodel.DependencyViewModel;
import com.singhajit.rubygems.gemdetails.viewmodel.ExternalLinksViewModel;
import com.singhajit.rubygems.trending.model.Dependency;
import com.singhajit.rubygems.trending.model.Gem;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class GemViewModel {
  private final Gem gem;
  private final StringResolver stringResolver;
  private ExternalLinksViewModel externalLinksViewModel;

  public GemViewModel(Gem gem, StringResolver stringResolver) {
    this.gem = gem;
    this.stringResolver = stringResolver;
    externalLinksViewModel = new ExternalLinksViewModel(gem);
  }

  public ExternalLinksViewModel getExternalLinksViewModel() {
    return externalLinksViewModel;
  }

  public String getName() {
    return gem.getName();
  }

  public String getDownloads() {
    return NumberFormat.getInstance().format(gem.getDownloads());
  }

  public String getVersion() {
    return gem.getVersion();
  }

  public String getRuntimeDependenciesText() {
    return stringResolver.getString(R.string.runtime_dependencies, gem.getDependencies().getRuntime().size());
  }

  public String getDevelopmentDependenciesText() {
    return stringResolver.getString(R.string.development_dependencies, gem.getDependencies().getDevelopment().size());
  }


  public List<DependencyViewModel> getDevelopmentDependencyViewModels() {
    return toDependencyViewModels(gem.getDependencies().getDevelopment());
  }

  public List<DependencyViewModel> getRuntimeDependencyViewModels() {
    return toDependencyViewModels(gem.getDependencies().getRuntime());
  }

  public String getLicenses() {
    return TextUtils.join(",", gem.getLicenses());
  }

  public String getInfo() {
    return gem.getInfo();
  }

  public String getVersionDownloads() {
    return NumberFormat.getInstance().format(gem.getVersionDownloads());
  }

  public String getAuthors() {
    return gem.getAuthors();
  }

  public Gem getGem() {
    return gem;
  }

  @NonNull
  private List<DependencyViewModel> toDependencyViewModels(List<Dependency> dependencies) {
    ArrayList<DependencyViewModel> dependencyViewModels = new ArrayList<>();
    for (Dependency dependency : dependencies) {
      dependencyViewModels.add(new DependencyViewModel(dependency));
    }
    return dependencyViewModels;
  }
}
