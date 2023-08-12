package com.tttn.api.handlers.write;

import com.tttn.api.entities.request.UpdateGiftVoucherPayLoad;
import com.tttn.api.entities.response.DefaultResponseData;
import com.tttn.api.handlers.APIHandler;
import com.tttn.api.resources.I18n;
import com.tttn.api.service.GiftVoucherService;
import com.tttn.api.utils.Global;

import java.util.TimeZone;

public class UpdateGiftVoucherHandler extends APIHandler<UpdateGiftVoucherPayLoad, DefaultResponseData> {

    static {
        System.setProperty("file.encoding", "UTF-8");
        TimeZone.setDefault(TimeZone.getTimeZone(System.getProperty("time.zone", "Asia/Ho_Chi_Minh")));
    }

    public UpdateGiftVoucherHandler() {
        super(UpdateGiftVoucherPayLoad.class);
    }

    @Override
    protected DefaultResponseData handle(UpdateGiftVoucherPayLoad request) throws Exception {
//        RequestValidationHelper.validateRequest(request);
        DefaultResponseData response = new DefaultResponseData();
        GiftVoucherService.update(request);
        I18n.load(Global.locale);
        String message = I18n.get("updateCartSuccess");
        response.setMessage(message);
        return response;
    }
}
