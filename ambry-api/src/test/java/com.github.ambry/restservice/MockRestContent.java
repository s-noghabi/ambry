package com.github.ambry.restservice;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Implmentation of RestContent that be used for tests. Input content should be JSON.
 */
public class MockRestContent implements RestContent {
  public static String CONTENT_KEY = "content";
  public static String IS_LAST_KEY = "isLast";

  /**
   * underlying data.
   * Contains: -
   * "content" - the actual content.
   * "isLast" - if this the last content (end marker).
   */
  private JSONObject data;

  public MockRestContent(JSONObject data)
      throws InstantiationException {
    if (data.has(IS_LAST_KEY) && data.has(CONTENT_KEY)) {
      this.data = data;
    } else {
      throw new InstantiationException("Given JSONObject cannot be converted to MockRestContent");
    }
  }

  public boolean isLast() {
    try {
      return data.getBoolean(IS_LAST_KEY);
    } catch (JSONException e) {
      return false;
    }
  }

  public byte[] getBytes() {
    try {
      return data.get(CONTENT_KEY).toString().getBytes();
    } catch (JSONException e) {
      return null;
    }
  }

  public void release() {
    //nothing to do
  }
}
