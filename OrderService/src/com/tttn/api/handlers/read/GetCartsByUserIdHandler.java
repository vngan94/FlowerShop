package com.tttn.api.handlers.read;

import com.tttn.api.dbresult.RGetCart;
import com.tttn.api.dbresult.RGetRole;
import com.tttn.api.entities.request.GetCartsByUserIdPayload;
import com.tttn.api.entities.response.DefaultResponseData;
import com.tttn.api.handlers.APIHandler;
import com.tttn.api.resources.I18n;
import com.tttn.api.service.CartService;
import com.tttn.api.utils.Global;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.stream.Collectors;

public class GetCartsByUserIdHandler extends APIHandler<GetCartsByUserIdPayload, DefaultResponseData> {
  static {
    System.setProperty("file.encoding", "UTF-8");
    TimeZone.setDefault(TimeZone.getTimeZone(System.getProperty("time.zone", "Asia/Ho_Chi_Minh")));
  }

  public GetCartsByUserIdHandler() {
    super(GetCartsByUserIdPayload.class);
  }

  @Override
  protected DefaultResponseData handle(GetCartsByUserIdPayload request) throws Exception {
    DefaultResponseData response = new DefaultResponseData();
    List<RGetCart> listQuery = new ArrayList<>();
    listQuery = CartService.getCartsByUserId(request);
    List<Map<String,Object>> data = listQuery.stream().map(RGetCart::getValueMap).collect(Collectors.toList());
    response.setData(data);
    I18n.load(Global.locale);
    String message = I18n.get("getCartSuccess");
    response.setMessage(message);
    return response;

  }
}
