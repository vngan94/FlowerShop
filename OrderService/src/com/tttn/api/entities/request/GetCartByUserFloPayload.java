package com.tttn.api.entities.request;

import com.tttn.api.entities.APIRequest;

public class GetCartByUserFloPayload extends APIRequest {

  private Integer userId;
  private Integer flowerId;

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public Integer getFlowerId() {
    return flowerId;
  }

  public void setFlowerId(Integer flowerId) {
    this.flowerId = flowerId;
  }
}
