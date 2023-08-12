package com.tttn.api.handlers.read;

import com.tttn.api.dbresult.RGetCart;
import com.tttn.api.entities.request.GetCartByIdPayload;
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

public class GetCartByIdHandler extends APIHandler<GetCartByIdPayload, DefaultResponseData> {
  static {
    System.setProperty("file.encoding", "UTF-8");
    TimeZone.setDefault(TimeZone.getTimeZone(System.getProperty("time.zone", "Asia/Ho_Chi_Minh")));
  }

  public GetCartByIdHandler() {
    super(GetCartByIdPayload.class);
  }

  @Override
  protected DefaultResponseData handle(GetCartByIdPayload request) throws Exception {
    DefaultResponseData response = new DefaultResponseData();
    RGetCart cart = CartService.getCartById(request);
    response.setData(cart != null ? cart.getValueMap() : null);
    I18n.load(Global.locale);
    String message = I18n.get("getCartSuccess");
    response.setMessage(message);
    return response;

  }
}
