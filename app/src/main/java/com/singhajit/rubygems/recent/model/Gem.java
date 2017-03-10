package com.singhajit.rubygems.recent.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Gem implements Parcelable {
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

  protected Gem(Parcel in) {
    name = in.readString();
    downloads = in.readDouble();
    version = in.readString();
    versionDownloads = in.readDouble();
    info = in.readString();
    licenses = in.createStringArrayList();
    authors = in.readString();
    projectURI = in.readString();
    homepageURI = in.readString();
    wikiURI = in.readString();
    documentationURI = in.readString();
    mailingListURI = in.readString();
    sourceCodeURI = in.readString();
    bugTrackerURI = in.readString();
    dependencies = in.readParcelable(Dependencies.class.getClassLoader());
  }

  public static final Creator<Gem> CREATOR = new Creator<Gem>() {
    @Override
    public Gem createFromParcel(Parcel in) {
      return new Gem(in);
    }

    @Override
    public Gem[] newArray(int size) {
      return new Gem[size];
    }
  };

  public String getName() {
    return name;
  }

  public Double getDownloads() {
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

  public boolean hasAuthors() {
    return authors != null && !authors.isEmpty();
  }

  public boolean hasLicenses() {
    return licenses != null && !licenses.isEmpty();
  }

  public String getProjectURI() {
    return projectURI;
  }

  public String getHomepageURI() {
    return homepageURI;
  }

  public boolean hasHomepageURI() {
    return homepageURI != null && !homepageURI.isEmpty();
  }

  public boolean hasProjectURI() {
    return projectURI != null && !projectURI.isEmpty();
  }

  public String getWikiURI() {
    return wikiURI;
  }

  public boolean hasWikiURI() {
    return wikiURI != null && !wikiURI.isEmpty();
  }

  public boolean hasDocumentationURI() {
    return documentationURI != null && !documentationURI.isEmpty();
  }

  public String getDocumentationURI() {
    return documentationURI;
  }

  public String getMailingListURI() {
    return mailingListURI;
  }

  public boolean hasMailingListURI() {
    return mailingListURI != null && !mailingListURI.isEmpty();
  }

  public String getSourceCodeURI() {
    return sourceCodeURI;
  }

  public boolean hasSourceCodeURI() {
    return sourceCodeURI != null && !sourceCodeURI.isEmpty();
  }

  public String getBugTrackerURI() {
    return bugTrackerURI;
  }

  public boolean hasBugTrackerURI() {
    return bugTrackerURI != null && !bugTrackerURI.isEmpty();
  }

  public Dependencies getDependencies() {
    return dependencies;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel parcel, int i) {
    parcel.writeString(name);
    parcel.writeDouble(downloads);
    parcel.writeString(version);
    parcel.writeDouble(versionDownloads);
    parcel.writeString(info);
    parcel.writeStringList(licenses);
    parcel.writeString(authors);
    parcel.writeString(projectURI);
    parcel.writeString(homepageURI);
    parcel.writeString(wikiURI);
    parcel.writeString(documentationURI);
    parcel.writeString(mailingListURI);
    parcel.writeString(sourceCodeURI);
    parcel.writeString(bugTrackerURI);
    parcel.writeParcelable(dependencies, i);
  }
}
