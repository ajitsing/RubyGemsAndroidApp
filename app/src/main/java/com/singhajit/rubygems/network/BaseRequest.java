package com.singhajit.rubygems.network;

import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

public class BaseRequest extends StringRequest {
  public BaseRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
    super(method, url, listener, errorListener);
  }

  @Override
  protected VolleyError parseNetworkError(VolleyError volleyError) {
    if (volleyError.networkResponse != null && volleyError.networkResponse.data != null) {
      return new VolleyError(new String(volleyError.networkResponse.data));
    } else {
      return new VolleyError("Not able to reach rubygems...");
    }
  }

  @Override
  protected Response<String> parseNetworkResponse(NetworkResponse response) {
    RubyGemsVolley volley = RubyGemsVolley.getInstance(null);
    if (volley != null) {
      Cache.Entry cacheEntry = new VolleyCacheManager().getCacheEntry(response);
      RubyGemsVolley.getInstance(null).getRequestQueue().getCache().put(getCacheKey(), cacheEntry);
    }
    return super.parseNetworkResponse(response);
  }
}
