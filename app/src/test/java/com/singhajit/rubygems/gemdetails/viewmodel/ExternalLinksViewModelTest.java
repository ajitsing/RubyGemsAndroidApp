package com.singhajit.rubygems.gemdetails.viewmodel;

import android.view.View;

import com.singhajit.rubygems.recent.model.Gem;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExternalLinksViewModelTest {

  @Test
  public void shouldReturnGemDetails() throws Exception {
    String bugTrackerUrl = "bug tracker url";
    String homePageUrl = "https://rubygems.org/gems/gocd";
    String wikiUrl = "wiki url";
    String documentationUrl = "documentation url";
    String mailingListUrl = "mailing list url";
    String sourceCodeUrl = "https://github.com/ajitsing/gocd";

    Gem gem = mock(Gem.class);
    when(gem.getHomepageURI()).thenReturn(homePageUrl);
    when(gem.getWikiURI()).thenReturn(wikiUrl);
    when(gem.getBugTrackerURI()).thenReturn(bugTrackerUrl);
    when(gem.getDocumentationURI()).thenReturn(documentationUrl);
    when(gem.getSourceCodeURI()).thenReturn(sourceCodeUrl);
    when(gem.getMailingListURI()).thenReturn(mailingListUrl);

    ExternalLinksViewModel viewModel = new ExternalLinksViewModel(gem);

    assertThat(viewModel.getBugTrackerURI(), is(bugTrackerUrl));
    assertThat(viewModel.getDocumentationURI(), is(documentationUrl));
    assertThat(viewModel.getHomepageURI(), is(homePageUrl));
    assertThat(viewModel.getSourceCodeURI(), is(sourceCodeUrl));
    assertThat(viewModel.getMailingListURI(), is(mailingListUrl));
    assertThat(viewModel.getWikiURI(), is(wikiUrl));
  }

  @Test
  public void shouldGetHomepageURIVisibility() throws Exception {
    Gem gemWithHomePageURL = mock(Gem.class);
    when(gemWithHomePageURL.hasHomepageURI()).thenReturn(true);

    Gem gemWithoutHomePageURL = mock(Gem.class);
    when(gemWithoutHomePageURL.hasHomepageURI()).thenReturn(false);

    ExternalLinksViewModel viewModelWithHomePageURL = new ExternalLinksViewModel(gemWithHomePageURL);
    ExternalLinksViewModel viewModelWithoutHomePageURL = new ExternalLinksViewModel(gemWithoutHomePageURL);

    assertThat(viewModelWithHomePageURL.getHomePageLinkVisibility().getVisibility(), is(View.VISIBLE));
    assertThat(viewModelWithoutHomePageURL.getHomePageLinkVisibility().getVisibility(), is(View.GONE));
  }

  @Test
  public void shouldGetDocumentationURIVisibility() throws Exception {
    Gem gemWithURL = mock(Gem.class);
    when(gemWithURL.hasDocumentationURI()).thenReturn(true);

    Gem gemWithoutURL = mock(Gem.class);
    when(gemWithoutURL.hasDocumentationURI()).thenReturn(false);

    ExternalLinksViewModel viewModelWithURL = new ExternalLinksViewModel(gemWithURL);
    ExternalLinksViewModel viewModelWithoutURL = new ExternalLinksViewModel(gemWithoutURL);

    assertThat(viewModelWithURL.getDocumentationLinkVisibility().getVisibility(), is(View.VISIBLE));
    assertThat(viewModelWithoutURL.getDocumentationLinkVisibility().getVisibility(), is(View.GONE));
  }

  @Test
  public void shouldGetWikiURIVisibility() throws Exception {
    Gem gemWithURL = mock(Gem.class);
    when(gemWithURL.hasWikiURI()).thenReturn(true);

    Gem gemWithoutURL = mock(Gem.class);
    when(gemWithoutURL.hasWikiURI()).thenReturn(false);

    ExternalLinksViewModel viewModelWithURL = new ExternalLinksViewModel(gemWithURL);
    ExternalLinksViewModel viewModelWithoutURL = new ExternalLinksViewModel(gemWithoutURL);

    assertThat(viewModelWithURL.getWikiLinkVisibility().getVisibility(), is(View.VISIBLE));
    assertThat(viewModelWithoutURL.getWikiLinkVisibility().getVisibility(), is(View.GONE));
  }

  @Test
  public void shouldGetSourceCodeURIVisibility() throws Exception {
    Gem gemWithURL = mock(Gem.class);
    when(gemWithURL.hasSourceCodeURI()).thenReturn(true);

    Gem gemWithoutURL = mock(Gem.class);
    when(gemWithoutURL.hasSourceCodeURI()).thenReturn(false);

    ExternalLinksViewModel viewModelWithURL = new ExternalLinksViewModel(gemWithURL);
    ExternalLinksViewModel viewModelWithoutURL = new ExternalLinksViewModel(gemWithoutURL);

    assertThat(viewModelWithURL.getSourceCodeLinkVisibility().getVisibility(), is(View.VISIBLE));
    assertThat(viewModelWithoutURL.getSourceCodeLinkVisibility().getVisibility(), is(View.GONE));
  }

  @Test
  public void shouldGetBugTrackerURIVisibility() throws Exception {
    Gem gemWithURL = mock(Gem.class);
    when(gemWithURL.hasBugTrackerURI()).thenReturn(true);

    Gem gemWithoutURL = mock(Gem.class);
    when(gemWithoutURL.hasBugTrackerURI()).thenReturn(false);

    ExternalLinksViewModel viewModelWithURL = new ExternalLinksViewModel(gemWithURL);
    ExternalLinksViewModel viewModelWithoutURL = new ExternalLinksViewModel(gemWithoutURL);

    assertThat(viewModelWithURL.getBugTrackerLinkVisibility().getVisibility(), is(View.VISIBLE));
    assertThat(viewModelWithoutURL.getBugTrackerLinkVisibility().getVisibility(), is(View.GONE));
  }

  @Test
  public void shouldGetMailingListURIVisibility() throws Exception {
    Gem gemWithURL = mock(Gem.class);
    when(gemWithURL.hasMailingListURI()).thenReturn(true);

    Gem gemWithoutURL = mock(Gem.class);
    when(gemWithoutURL.hasMailingListURI()).thenReturn(false);

    ExternalLinksViewModel viewModelWithURL = new ExternalLinksViewModel(gemWithURL);
    ExternalLinksViewModel viewModelWithoutURL = new ExternalLinksViewModel(gemWithoutURL);

    assertThat(viewModelWithURL.getMailingListLinkVisibility().getVisibility(), is(View.VISIBLE));
    assertThat(viewModelWithoutURL.getMailingListLinkVisibility().getVisibility(), is(View.GONE));
  }
}