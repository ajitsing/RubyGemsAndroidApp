package com.singhajit.rubygems.trending.view;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.singhajit.rubygems.R;
import com.singhajit.rubygems.core.APIClient;
import com.singhajit.rubygems.core.INotifier;
import com.singhajit.rubygems.databinding.TrendingBinding;
import com.singhajit.rubygems.trending.presenter.TrendingPresenter;
import com.singhajit.rubygems.trending.viewmodel.TrendingViewModel;

public class TrendingGemsFragment extends Fragment implements TrendingView {

  private TrendingBinding binding;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    binding = DataBindingUtil.inflate(inflater, R.layout.trending_fragment, container, false);
    TrendingPresenter presenter = new TrendingPresenter((APIClient) getActivity(), this);
    presenter.render();
    return binding.getRoot();
  }

  @Override
  public void render(TrendingViewModel viewModel) {
    binding.gemList.setAdapter(GemAdapter.newInstance(viewModel));
    binding.gemList.setLayoutManager(new LinearLayoutManager(getActivity()));
  }

  @Override
  public void notify(String message) {
    ((INotifier) getActivity()).notify(message);
  }
}