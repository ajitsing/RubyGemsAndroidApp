package com.singhajit.rubygems.profile.request;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.singhajit.rubygems.core.RubyGemsAPIs;
import com.singhajit.rubygems.network.BaseRequest;

import java.util.HashMap;
import java.util.Map;

public class UserGemsRequest extends BaseRequest {
  private final String apiKey;

  public UserGemsRequest(String apiKey, Response.Listener<String> listener, Response.ErrorListener errorListener) {
    super(Method.GET, RubyGemsAPIs.USER_GEMS, listener, errorListener);
    this.apiKey = apiKey;
    setShouldCache(false);
  }

  @Override
  public Map<String, String> getHeaders() throws AuthFailureError {
    HashMap<String, String> myHeaders = new HashMap<>();
    myHeaders.put("Authorization", apiKey);
    return myHeaders;
  }
}
