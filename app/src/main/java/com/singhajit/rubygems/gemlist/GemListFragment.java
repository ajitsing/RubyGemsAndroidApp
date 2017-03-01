package com.singhajit.rubygems.gemlist;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.singhajit.rubygems.R;
import com.singhajit.rubygems.databinding.GemListBinding;
import com.singhajit.rubygems.gemlist.viewmodel.GemListViewModel;
import com.singhajit.rubygems.trending.model.Gem;

import java.util.ArrayList;

public class GemListFragment extends Fragment {
  public static final String GEM_LIST = "GEM_LIST";

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    ArrayList<Gem> gems = getArguments().getParcelableArrayList(GEM_LIST);
    GemListBinding binding = DataBindingUtil.inflate(inflater, R.layout.gemlist_fragment, container, false);
    binding.gemList.setAdapter(GemAdapter.newInstance(new GemListViewModel(gems)));
    binding.gemList.setLayoutManager(new LinearLayoutManager(getActivity()));
    return binding.getRoot();
  }
}
