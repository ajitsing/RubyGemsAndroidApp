package com.singhajit.rubygems.gemlist.presenter;

import com.singhajit.rubygems.gemlist.GemListAction;
import com.singhajit.rubygems.gemlist.viewmodel.GemViewModel;
import com.singhajit.rubygems.recent.model.Gem;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GemListPresenterTest {
  @Test
  public void shouldNavigateToGemDetailPage() throws Exception {
    Gem gem = mock(Gem.class);
    GemViewModel gemViewModel = mock(GemViewModel.class);
    when(gemViewModel.getGem()).thenReturn(gem);
    GemListAction gemListAction = mock(GemListAction.class);
    GemListPresenter presenter = new GemListPresenter(gemListAction);

    presenter.onGemClicked(gemViewModel);

    verify(gemListAction).navigateToGemPage(gem);
  }
}