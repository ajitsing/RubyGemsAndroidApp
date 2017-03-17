package com.singhajit.rubygems.gemlist.viewmodel;

import com.singhajit.rubygems.core.StringResolver;
import com.singhajit.rubygems.recent.model.Gem;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GemListViewModelTest {

  @Test
  public void shouldGetGemViewModels() throws Exception {
    ArrayList<Gem> gems = new ArrayList<>();
    Gem gem1 = mock(Gem.class);
    when(gem1.getName()).thenReturn("gocd");
    Gem gem2 = mock(Gem.class);
    when(gem2.getName()).thenReturn("gocd_pre_push");
    gems.add(gem1);
    gems.add(gem2);

    GemListViewModel viewModel = new GemListViewModel(gems, mock(StringResolver.class));
    List<GemViewModel> gemViewModels = viewModel.getGemViewModels();

    assertThat(gemViewModels.size(), is(2));
    assertThat(gemViewModels.get(0).getName(), is("gocd"));
    assertThat(gemViewModels.get(1).getName(), is("gocd_pre_push"));
  }
}