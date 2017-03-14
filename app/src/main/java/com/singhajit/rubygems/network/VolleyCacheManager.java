package com.singhajit.rubygems.network;

import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.toolbox.HttpHeaderParser;

import java.util.Map;

public class VolleyCacheManager {
  private final static long HOUR = 60 * 60 * 1000;
  private final static long A_DAY = 24 * HOUR;

  public Cache.Entry getCacheEntry(NetworkResponse response) {
    long now = System.currentTimeMillis();

    Map<String, String> headers = response.headers;
    long serverDate = 0;
    String serverEtag;
    String headerValue;

    headerValue = headers.get("Date");
    if (headerValue != null) {
      serverDate = HttpHeaderParser.parseDateAsEpoch(headerValue);
    }

    serverEtag = headers.get("ETag");

    final long cacheHitButRefreshed = getCacheHitButRefreshedTime();
    final long cacheExpired = getCacheExpiryTime();
    final long softExpire = now + cacheHitButRefreshed;
    final long ttl = now + cacheExpired;

    Cache.Entry entry = new Cache.Entry();
    entry.data = response.data;
    entry.etag = serverEtag;
    entry.softTtl = softExpire;
    entry.ttl = ttl;
    entry.serverDate = serverDate;
    entry.responseHeaders = headers;

    return entry;
  }

  public long getCacheHitButRefreshedTime() {
    return getCacheExpiryTime();
  }

  public long getCacheExpiryTime() {
    return A_DAY * 365;
  }
}
