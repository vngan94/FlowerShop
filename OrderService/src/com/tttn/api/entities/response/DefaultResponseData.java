package com.tttn.api.entities.response;

import com.tttn.api.entities.ResponseData;

public class DefaultResponseData extends ResponseData {
  private String message;

  private Object data;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }
}
