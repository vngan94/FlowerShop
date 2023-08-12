package com.tttn.api.entities.request;

import com.tttn.api.entities.APIRequest;

public class GetGiftVoucherByKeyPayload extends APIRequest {

  private String key;

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }
}
