package com.singhajit.rubygems.trending.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Gem {
  private String name;
  private double downloads;
  private String version;
  @SerializedName("version_downloads")
  private double versionDownloads;
  private String info;
  private List<String> licenses;
  private String authors;
  @SerializedName("project_uri")
  private String projectURI;
  @SerializedName("homepage_uri")
  private String homepageURI;
  @SerializedName("wiki_uri")
  private String wikiURI;
  @SerializedName("documentation_uri")
  private String documentationURI;
  @SerializedName("mailing_list_uri")
  private String mailingListURI;
  @SerializedName("source_code_uri")
  private String sourceCodeURI;
  @SerializedName("bug_tracker_uri")
  private String bugTrackerURI;
  private Dependencies dependencies;

  public Gem(String name, double downloads, String version, String info) {
    this.name = name;
    this.downloads = downloads;
    this.version = version;
    this.info = info;
  }

  public String getName() {
    return name;
  }

  public double getDownloads() {
    return downloads;
  }

  public String getVersion() {
    return version;
  }

  public String getInfo() {
    return info;
  }

  public double getVersionDownloads() {
    return versionDownloads;
  }

  public List<String> getLicenses() {
    return licenses;
  }

  public String getAuthors() {
    return authors;
  }

  public String getProjectURI() {
    return projectURI;
  }

  public String getHomepageURI() {
    return homepageURI;
  }

  public String getWikiURI() {
    return wikiURI;
  }

  public String getDocumentationURI() {
    return documentationURI;
  }

  public String getMailingListURI() {
    return mailingListURI;
  }

  public String getSourceCodeURI() {
    return sourceCodeURI;
  }

  public String getBugTrackerURI() {
    return bugTrackerURI;
  }

  public Dependencies getDependencies() {
    return dependencies;
  }
}
