package com.tttn.api.entities.request;

import com.tttn.api.entities.APIRequest;

public class GetCartByIdPayload extends APIRequest {

  private Integer id;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
}
