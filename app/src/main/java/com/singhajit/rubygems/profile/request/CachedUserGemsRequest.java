package com.singhajit.rubygems.profile.request;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.singhajit.rubygems.core.RubyGemsAPIs;
import com.singhajit.rubygems.network.CachedRequest;

import java.util.Map;

public class CachedUserGemsRequest extends CachedRequest {
  private final String apiKey;

  public CachedUserGemsRequest(String apiKey, Response.Listener<String> listener, Response.ErrorListener errorListener) {
    super(Method.GET, RubyGemsAPIs.USER_GEMS, listener, errorListener);
    this.apiKey = apiKey;
  }

  @Override
  public Map<String, String> getHeaders() throws AuthFailureError {
    return new UserGemsRequest(apiKey, null, null).getHeaders();
  }
}
