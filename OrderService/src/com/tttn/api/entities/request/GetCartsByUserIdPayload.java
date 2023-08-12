package com.tttn.api.entities.request;

import com.tttn.api.entities.APIRequest;

public class GetCartsByUserIdPayload extends APIRequest {

  private Integer userId;

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }
}
