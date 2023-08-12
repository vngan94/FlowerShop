package com.tttn.api.handlers.read;

import com.tttn.api.dbresult.RGetUser;
import com.tttn.api.entities.request.GetUserByKeyPayload;
import com.tttn.api.entities.response.DefaultResponseData;
import com.tttn.api.handlers.APIHandler;
import com.tttn.api.resources.I18n;
import com.tttn.api.service.AccountService;
import com.tttn.api.utils.Global;

import java.util.TimeZone;

public class GetUserByKeyHandler extends APIHandler<GetUserByKeyPayload, DefaultResponseData> {
  static {
    System.setProperty("file.encoding", "UTF-8");
    TimeZone.setDefault(TimeZone.getTimeZone(System.getProperty("time.zone", "Asia/Ho_Chi_Minh")));
  }

  public GetUserByKeyHandler() {
    super(GetUserByKeyPayload.class);
  }

  @Override
  protected DefaultResponseData handle(GetUserByKeyPayload request) throws Exception {
    DefaultResponseData response = new DefaultResponseData();
    RGetUser user = AccountService.getUserByKey(request);
    response.setData(user != null ? user.getValueMap() : null);
    I18n.load(Global.locale);
    String message = I18n.get("getUserSuccess");
    response.setMessage(message);
    return response;

  }
}
