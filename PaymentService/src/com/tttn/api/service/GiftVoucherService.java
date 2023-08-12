package com.tttn.api.service;

import com.tttn.api.dbresult.RGetGiftVoucher;
import com.tttn.api.entities.request.GetGiftVoucherByKeyPayload;
import com.tttn.api.entities.request.PostGiftVoucherPayLoad;
import com.tttn.api.entities.request.UpdateGiftVoucherPayLoad;
import com.tttn.api.handlers.write.PostGiftVoucherHandler;
import com.tttn.api.repository.AccountRepository;
import com.tttn.api.repository.GiftVoucherRepository;

public class GiftVoucherService {

  private static GiftVoucherService giftVoucherService = null;

  public static GiftVoucherService ins() {
    if (giftVoucherService == null) {
      giftVoucherService = new GiftVoucherService();
    }
    return giftVoucherService;
  }


  public static RGetGiftVoucher getGiftVoucherByKey(GetGiftVoucherByKeyPayload request) throws Exception{
    return GiftVoucherRepository.getGiftVoucherByKey(request);
  }

  public static int create(PostGiftVoucherPayLoad request) throws Exception {
    return GiftVoucherRepository.create(request);
  }

  public static void update(UpdateGiftVoucherPayLoad request) throws Exception{
    GiftVoucherRepository.update(request);
  }
}
