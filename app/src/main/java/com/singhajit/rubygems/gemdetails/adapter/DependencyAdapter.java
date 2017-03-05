package com.singhajit.rubygems.gemdetails.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.singhajit.rubygems.R;
import com.singhajit.rubygems.databinding.DependencyBinding;
import com.singhajit.rubygems.gemdetails.viewmodel.DependencyViewModel;

import java.util.List;

public class DependencyAdapter extends RecyclerView.Adapter<DependencyViewHolder> {
  private final List<DependencyViewModel> dependencyViewModels;

  public static DependencyAdapter newInstance(List<DependencyViewModel> dependencyViewModels) {
    return new DependencyAdapter(dependencyViewModels);
  }

  private DependencyAdapter(List<DependencyViewModel> dependencyViewModels) {
    this.dependencyViewModels = dependencyViewModels;
  }

  @Override
  public DependencyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    DependencyBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.dependency, parent, false);
    return new DependencyViewHolder(binding);
  }

  @Override
  public void onBindViewHolder(DependencyViewHolder holder, int position) {
    holder.render(dependencyViewModels.get(position));
  }

  @Override
  public int getItemCount() {
    return dependencyViewModels.size();
  }
}

class DependencyViewHolder extends RecyclerView.ViewHolder {
  private final DependencyBinding binding;

  DependencyViewHolder(DependencyBinding binding) {
    super(binding.getRoot());
    this.binding = binding;
  }

  void render(DependencyViewModel dependencyViewModel) {
    binding.setViewModel(dependencyViewModel);
  }
}
