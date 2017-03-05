package com.singhajit.rubygems.gemdetails;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.singhajit.rubygems.R;
import com.singhajit.rubygems.core.BaseActivity;
import com.singhajit.rubygems.databinding.EmbeddedWebBinding;

public class EmbeddedWebActivity extends BaseActivity {
  public final static String LINK_EXTRA = "LINK";
  private EmbeddedWebBinding binding;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    final String link = getIntent().getStringExtra(LINK_EXTRA);
    super.onCreate(savedInstanceState);

    binding = DataBindingUtil.setContentView(this, R.layout.embedded_web_activity);
    setSupportActionBar(binding.toolbar);

    getSupportActionBar().setHomeButtonEnabled(true);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    setTitle(link);

    final WebView webView = binding.webView;
    webView.getSettings().setJavaScriptEnabled(true);

    webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);

    webView.getSettings().setBuiltInZoomControls(true);
    webView.getSettings().setUseWideViewPort(true);
    webView.getSettings().setLoadWithOverviewMode(true);

    webView.setWebViewClient(getClient(link));
    webView.loadUrl(link);
  }

  @NonNull
  private WebViewClient getClient(final String link) {
    return new WebViewClient() {
      @Override
      public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        Snackbar.make(binding.getRoot(), error.toString(), Snackbar.LENGTH_LONG).show();
      }

      @Override
      public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        view.loadUrl(link);
        return true;
      }

      @Override
      public void onPageFinished(WebView view, String url) {
        binding.progressBar.setVisibility(View.GONE);
      }
    };
  }
}
