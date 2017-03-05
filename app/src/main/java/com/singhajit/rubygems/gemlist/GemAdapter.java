package com.singhajit.rubygems.gemlist;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.singhajit.rubygems.R;
import com.singhajit.rubygems.databinding.GemCardBinding;
import com.singhajit.rubygems.gemlist.presenter.GemListPresenter;
import com.singhajit.rubygems.gemlist.viewmodel.GemListViewModel;
import com.singhajit.rubygems.gemlist.viewmodel.GemViewModel;

public class GemAdapter extends RecyclerView.Adapter<GemViewHolder> {
  private GemListViewModel gemListViewModel;
  private final GemListPresenter presenter;

  public GemAdapter(GemListViewModel gemListViewModel, GemListPresenter presenter) {
    this.gemListViewModel = gemListViewModel;
    this.presenter = presenter;
  }

  @Override
  public GemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    GemCardBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.gem_card, parent, false);
    return new GemViewHolder(binding);
  }

  @Override
  public void onBindViewHolder(GemViewHolder holder, int position) {
    holder.render(gemListViewModel.getGemViewModels().get(position), presenter);
  }

  @Override
  public int getItemCount() {
    return gemListViewModel.getGemViewModels().size();
  }
}

class GemViewHolder extends RecyclerView.ViewHolder {
  private final GemCardBinding binding;

  GemViewHolder(GemCardBinding binding) {
    super(binding.getRoot());
    this.binding = binding;
  }

  void render(GemViewModel gemViewModel, GemListPresenter presenter) {
    binding.setViewModel(gemViewModel);
    binding.setPresenter(presenter);
  }
}
