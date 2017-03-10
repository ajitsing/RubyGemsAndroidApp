package com.singhajit.rubygems.profile.request;

import android.util.Base64;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.singhajit.rubygems.core.RubyGemsAPIs;
import com.singhajit.rubygems.network.BaseRequest;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends BaseRequest {
  private final String username;
  private final String password;

  public LoginRequest(String username, String password, Response.Listener<String> listener, Response.ErrorListener errorListener) {
    super(Method.GET, RubyGemsAPIs.API_KEY, listener, errorListener);
    this.username = username;
    this.password = password;
    setShouldCache(false);
  }

  @Override
  public Map<String, String> getHeaders() throws AuthFailureError {
    String usernamePass = "";
    try {
      usernamePass = Base64.encodeToString((username + ":" + password).getBytes("UTF-8"), Base64.NO_WRAP);
    } catch (UnsupportedEncodingException e) {
      Log.e(LoginRequest.class.getSimpleName(), e.getMessage());
    }
    Map<String, String> myHeaders = new HashMap<>();
    myHeaders.put("Authorization", "Basic " + usernamePass);
    return myHeaders;
  }
}
