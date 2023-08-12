package com.tttn.api.handlers.read;

import com.tttn.api.dbresult.RGetRole;
import com.tttn.api.entities.APIRequest;
import com.tttn.api.entities.request.CheckLoginPayload;
import com.tttn.api.entities.response.DefaultResponseData;
import com.tttn.api.entities.response.GetAllRoleResponseData;
import com.tttn.api.handlers.APIHandler;
import com.tttn.api.resources.I18n;
import com.tttn.api.service.AccountService;
import com.tttn.api.service.RoleService;
import com.tttn.api.utils.Global;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.stream.Collectors;

public class CheckLoginHandler extends APIHandler<CheckLoginPayload, DefaultResponseData> {
  static {
    System.setProperty("file.encoding", "UTF-8");
    TimeZone.setDefault(TimeZone.getTimeZone(System.getProperty("time.zone", "Asia/Ho_Chi_Minh")));
  }

  public CheckLoginHandler() {
    super(CheckLoginPayload.class);
  }

  @Override
  protected DefaultResponseData handle(CheckLoginPayload request) throws Exception {
//    System.out.println("checklogin");
    DefaultResponseData response = new DefaultResponseData();
    Integer checkLogin = AccountService.checkLogin(request);
    response.setData(checkLogin);
    I18n.load(Global.locale);
    String message = I18n.get("checkLoginSuccess");
    response.setMessage(message);
    return response;

  }
}
