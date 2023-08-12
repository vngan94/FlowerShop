package com.tttn.api.handlers.read;

import com.tttn.api.dbresult.RGetCart;
import com.tttn.api.entities.request.GetCartByUserFloPayload;
import com.tttn.api.entities.response.DefaultResponseData;
import com.tttn.api.handlers.APIHandler;
import com.tttn.api.resources.I18n;
import com.tttn.api.service.CartService;
import com.tttn.api.utils.Global;

import java.util.TimeZone;

public class GetCartByUserFloHandler extends APIHandler<GetCartByUserFloPayload, DefaultResponseData> {
  static {
    System.setProperty("file.encoding", "UTF-8");
    TimeZone.setDefault(TimeZone.getTimeZone(System.getProperty("time.zone", "Asia/Ho_Chi_Minh")));
  }

  public GetCartByUserFloHandler() {
    super(GetCartByUserFloPayload.class);
  }

  @Override
  protected DefaultResponseData handle(GetCartByUserFloPayload request) throws Exception {
    DefaultResponseData response = new DefaultResponseData();
    RGetCart cart = CartService.getCartByUserFlo(request);
    response.setData(cart != null ? cart.getValueMap() : null);
    I18n.load(Global.locale);
    String message = I18n.get("getCartSuccess");
    response.setMessage(message);
    return response;

  }
}
