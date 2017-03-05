package com.singhajit.rubygems.gemdetails;

import android.app.ProgressDialog;
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

public class EmbeddedWebActivity extends BaseActivity {
  public final static String LINK_EXTRA = "LINK";

  @Override
  public void onCreate(Bundle savedInstanceState) {
    final String link = getIntent().getStringExtra(LINK_EXTRA);
    super.onCreate(savedInstanceState);
    final WebView webView = new WebView(this);
    webView.getSettings().setJavaScriptEnabled(true);

    webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);

    webView.getSettings().setBuiltInZoomControls(true);
    webView.getSettings().setUseWideViewPort(true);
    webView.getSettings().setLoadWithOverviewMode(true);

    final ProgressDialog progressDialog = new ProgressDialog(this);
    progressDialog.setCancelable(false);
    progressDialog.setMessage(getString(R.string.loading));
    progressDialog.show();

    webView.setWebViewClient(getClient(link, webView, progressDialog));

    webView.loadUrl(link);
    setContentView(webView);
  }

  @NonNull
  private WebViewClient getClient(final String link, final WebView webView, final ProgressDialog progressDialog) {
    return new WebViewClient() {
      @Override
      public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        Snackbar.make(webView, error.toString(), Snackbar.LENGTH_LONG).show();
      }

      @Override
      public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        view.loadUrl(link);
        return true;
      }

      @Override
      public void onPageFinished(WebView view, String url) {
        progressDialog.dismiss();
      }
    };
  }
}
