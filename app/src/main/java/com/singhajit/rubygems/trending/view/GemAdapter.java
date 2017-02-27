package com.singhajit.rubygems.trending.view;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.singhajit.rubygems.R;
import com.singhajit.rubygems.databinding.GemCardBinding;
import com.singhajit.rubygems.trending.viewmodel.GemViewModel;
import com.singhajit.rubygems.trending.viewmodel.TrendingViewModel;

public class GemAdapter extends RecyclerView.Adapter<GemViewHolder> {
  private TrendingViewModel trendingViewModel;

  public static GemAdapter newInstance(TrendingViewModel trendingViewModel) {
    return new GemAdapter(trendingViewModel);
  }

  private GemAdapter(TrendingViewModel trendingViewModel) {
    this.trendingViewModel = trendingViewModel;
  }

  @Override
  public GemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    GemCardBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.gem_card, parent, false);
    return new GemViewHolder(binding);
  }

  @Override
  public void onBindViewHolder(GemViewHolder holder, int position) {
    holder.render(trendingViewModel.getGemViewModels().get(position));
  }

  @Override
  public int getItemCount() {
    return trendingViewModel.getGemViewModels().size();
  }
}

class GemViewHolder extends RecyclerView.ViewHolder {
  private final GemCardBinding binding;

  GemViewHolder(GemCardBinding binding) {
    super(binding.getRoot());
    this.binding = binding;
  }

  void render(GemViewModel gemViewModel) {
    binding.setViewModel(gemViewModel);
  }
}
