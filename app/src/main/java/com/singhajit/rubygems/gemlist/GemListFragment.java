package com.singhajit.rubygems.gemlist;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.singhajit.rubygems.R;
import com.singhajit.rubygems.core.StringResolver;
import com.singhajit.rubygems.databinding.GemListBinding;
import com.singhajit.rubygems.gemdetails.GemActivity;
import com.singhajit.rubygems.gemlist.presenter.GemListPresenter;
import com.singhajit.rubygems.gemlist.viewmodel.GemListViewModel;
import com.singhajit.rubygems.trending.model.Gem;

import java.util.ArrayList;

import static com.singhajit.rubygems.gemdetails.GemActivity.GEM_EXTRA;

public class GemListFragment extends Fragment implements GemListAction {
  public static final String GEM_LIST = "GEM_LIST";

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    ArrayList<Gem> gems = getArguments().getParcelableArrayList(GEM_LIST);
    GemListBinding binding = DataBindingUtil.inflate(inflater, R.layout.gemlist_fragment, container, false);
    GemListPresenter presenter = new GemListPresenter(this);
    GemAdapter adapter = new GemAdapter(new GemListViewModel(gems, new StringResolver(getActivity())), presenter);
    binding.gemList.setAdapter(adapter);
    binding.gemList.setLayoutManager(new LinearLayoutManager(getActivity()));
    binding.gemList.setNestedScrollingEnabled(false);
    return binding.getRoot();
  }

  @Override
  public void navigateToGemPage(Gem gem) {
    Intent intent = new Intent(getActivity(), GemActivity.class);
    intent.putExtra(GEM_EXTRA, gem);
    startActivity(intent);
  }
}
