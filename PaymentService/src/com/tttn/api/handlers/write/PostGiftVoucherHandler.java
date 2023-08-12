package com.tttn.api.handlers.write;

import com.tttn.api.entities.request.PostGiftVoucherPayLoad;
import com.tttn.api.entities.response.DefaultResponseData;
import com.tttn.api.handlers.APIHandler;
import com.tttn.api.resources.I18n;
import com.tttn.api.service.GiftVoucherService;
import com.tttn.api.utils.Global;

import java.util.TimeZone;

public class PostGiftVoucherHandler extends APIHandler<PostGiftVoucherPayLoad, DefaultResponseData> {

    static {
        System.setProperty("file.encoding", "UTF-8");
        TimeZone.setDefault(TimeZone.getTimeZone(System.getProperty("time.zone", "Asia/Ho_Chi_Minh")));
    }

    public PostGiftVoucherHandler() {
        super(PostGiftVoucherPayLoad.class);
    }

    @Override
    protected DefaultResponseData handle(PostGiftVoucherPayLoad request) throws Exception {
//        RequestValidationHelper.validateRequest(request);
        DefaultResponseData response = new DefaultResponseData();
        Integer id =  GiftVoucherService.create(request);
        response.setData(id);
        I18n.load(Global.locale);
        String message = I18n.get("postGiftVoucherSuccess");
        response.setMessage(message);
        return response;
    }
}
