package com.singhajit.rubygems.gemlist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.singhajit.rubygems.core.StringResolver;
import com.singhajit.rubygems.gemdetails.GemActivity;
import com.singhajit.rubygems.gemlist.presenter.GemListPresenter;
import com.singhajit.rubygems.gemlist.viewmodel.GemListViewModel;
import com.singhajit.rubygems.recent.model.Gem;

import java.util.List;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

import static com.singhajit.rubygems.gemdetails.GemActivity.GEM_EXTRA;

public class GemListRenderer implements GemListAction {
  private final List<Gem> gems;
  private final RecyclerView view;

  public GemListRenderer(List<Gem> gems, RecyclerView view) {
    this.gems = gems;
    this.view = view;
  }

  public void render(boolean disableAnimation) {
    GemListPresenter presenter = new GemListPresenter(this);
    GemAdapter adapter = new GemAdapter(new GemListViewModel(gems, new StringResolver(view.getContext())), presenter);
    view.setAdapter(disableAnimation ? adapter : new ScaleInAnimationAdapter(adapter));
    view.setLayoutManager(new LinearLayoutManager(view.getContext()));
    view.setNestedScrollingEnabled(false);
  }

  @Override
  public void navigateToGemPage(Gem gem) {
    Context context = view.getContext();
    Intent intent = new Intent(context, GemActivity.class);
    intent.putExtra(GEM_EXTRA, gem);
    context.startActivity(intent);
  }
}
