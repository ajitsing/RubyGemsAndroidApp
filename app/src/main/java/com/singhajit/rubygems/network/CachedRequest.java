package com.singhajit.rubygems.network;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;

public class CachedRequest extends BaseRequest {
  public CachedRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
    super(method, url, listener, errorListener);
  }

  @Override
  protected Response<String> parseNetworkResponse(NetworkResponse response) {
    String stringResponse = new String(response.data);
    return Response.success(stringResponse, new VolleyCacheManager().getCacheEntry(response));
  }
}
