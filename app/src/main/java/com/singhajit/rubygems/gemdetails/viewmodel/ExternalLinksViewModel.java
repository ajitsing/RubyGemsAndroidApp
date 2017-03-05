package com.singhajit.rubygems.gemdetails.viewmodel;


import com.singhajit.rubygems.core.ViewVisibility;
import com.singhajit.rubygems.trending.model.Gem;

public class ExternalLinksViewModel {
  private final Gem gem;

  public ExternalLinksViewModel(Gem gem) {
    this.gem = gem;
  }

  public String getProjectURI() {
    return gem.getProjectURI();
  }

  public String getHomepageURI() {
    return gem.getHomepageURI();
  }

  public String getWikiURI() {
    return gem.getWikiURI();
  }

  public String getDocumentationURI() {
    return gem.getDocumentationURI();
  }

  public String getMailingListURI() {
    return gem.getMailingListURI();
  }

  public String getSourceCodeURI() {
    return gem.getSourceCodeURI();
  }

  public String getBugTrackerURI() {
    return gem.getBugTrackerURI();
  }


  public ViewVisibility getHomePageLinkVisibility() {
    return new ViewVisibility(gem.getHomepageURI() != null);
  }

  public ViewVisibility getSourceCodeLinkVisibility() {
    return new ViewVisibility(gem.getSourceCodeURI() != null);
  }

  public ViewVisibility getWikiLinkVisibility() {
    return new ViewVisibility(gem.getWikiURI() != null);
  }

  public ViewVisibility getMailingListLinkVisibility() {
    return new ViewVisibility(gem.getMailingListURI() != null);
  }

  public ViewVisibility getBugTrackerLinkVisibility() {
    return new ViewVisibility(gem.getBugTrackerURI() != null);
  }

  public ViewVisibility getDocumentationLinkVisibility() {
    return new ViewVisibility(gem.getDocumentationURI() != null);
  }
}
