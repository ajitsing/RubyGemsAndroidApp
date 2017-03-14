package com.singhajit.rubygems.gemdetails;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.singhajit.rubygems.R;
import com.singhajit.rubygems.core.BaseActivity;
import com.singhajit.rubygems.core.StringResolver;
import com.singhajit.rubygems.databinding.GemBinding;
import com.singhajit.rubygems.gemdetails.presenter.GemPresenter;
import com.singhajit.rubygems.gemlist.viewmodel.GemViewModel;
import com.singhajit.rubygems.recent.model.Gem;

public class GemActivity extends BaseActivity implements GemView {
  public static final String GEM_EXTRA = "GEM_EXTRA";
  private GemPresenter presenter;
  private GemViewModel viewModel;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Gem gem = getIntent().getParcelableExtra(GEM_EXTRA);
    GemBinding binding = DataBindingUtil.setContentView(this, R.layout.gem_activity);
    viewModel = new GemViewModel(gem, new StringResolver(this));
    binding.setViewModel(viewModel);

    presenter = new GemPresenter(this);
    binding.setPresenter(presenter);

    Toolbar toolbar = binding.toolbar;
    setSupportActionBar(toolbar);

    getSupportActionBar().setHomeButtonEnabled(true);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    setTitle(viewModel.getName());
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.gem_page_menu, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == R.id.share) {
      presenter.shareGem(viewModel);
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void navigateToExternalLink(String link) {
    Intent intent = new Intent(this, EmbeddedWebActivity.class);
    intent.putExtra(EmbeddedWebActivity.LINK_EXTRA, link);
    startActivity(intent);
  }

  @Override
  public void openSendApplicationChooser(String message) {
    ClipboardManager clipboardService = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
    clipboardService.setPrimaryClip(ClipData.newPlainText("GemSharingMsg", message));
    Toast.makeText(this, R.string.copied_gem_details_msg, Toast.LENGTH_LONG).show();

    Intent share = new Intent(Intent.ACTION_SEND);
    share.setType("text/plain");
    share.putExtra(Intent.EXTRA_TEXT, message);

    startActivity(Intent.createChooser(share, getString(R.string.share_dialog_message)));
  }
}
