package com.tttn.api.handlers.read;

import com.tttn.api.dbresult.RGetGiftVoucher;
import com.tttn.api.entities.request.GetGiftVoucherByKeyPayload;
import com.tttn.api.entities.response.DefaultResponseData;
import com.tttn.api.handlers.APIHandler;
import com.tttn.api.resources.I18n;
import com.tttn.api.service.AccountService;
import com.tttn.api.service.GiftVoucherService;
import com.tttn.api.utils.Global;

import java.util.TimeZone;

public class GetGiftVoucherByKeyHandler extends APIHandler<GetGiftVoucherByKeyPayload, DefaultResponseData> {
  static {
    System.setProperty("file.encoding", "UTF-8");
    TimeZone.setDefault(TimeZone.getTimeZone(System.getProperty("time.zone", "Asia/Ho_Chi_Minh")));
  }

  public GetGiftVoucherByKeyHandler() {
    super(GetGiftVoucherByKeyPayload.class);
  }

  @Override
  protected DefaultResponseData handle(GetGiftVoucherByKeyPayload request) throws Exception {
    DefaultResponseData response = new DefaultResponseData();
    RGetGiftVoucher giftVoucher = GiftVoucherService.getGiftVoucherByKey(request);
    response.setData(giftVoucher != null ? giftVoucher.getValueMap() : null);
    I18n.load(Global.locale);
    String message = I18n.get("getGiftVoucherSuccess");
    response.setMessage(message);
    return response;

  }
}
