package com.tttn.api.entities.request;

import com.tttn.api.entities.APIRequest;

public class CheckLoginPayload extends APIRequest {

  private String loginName;
  private String password;

  public String getLoginName() {
    return loginName;
  }

  public void setLoginName(String loginName) {
    this.loginName = loginName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
