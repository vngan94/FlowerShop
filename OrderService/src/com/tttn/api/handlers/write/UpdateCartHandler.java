package com.tttn.api.handlers.write;

import com.tttn.api.entities.request.UpdateCartPayLoad;
import com.tttn.api.entities.response.DefaultResponseData;
import com.tttn.api.handlers.APIHandler;
import com.tttn.api.resources.I18n;
import com.tttn.api.service.CartService;
import com.tttn.api.utils.Global;

import java.util.TimeZone;

public class UpdateCartHandler extends APIHandler<UpdateCartPayLoad, DefaultResponseData> {

    static {
        System.setProperty("file.encoding", "UTF-8");
        TimeZone.setDefault(TimeZone.getTimeZone(System.getProperty("time.zone", "Asia/Ho_Chi_Minh")));
    }

    public UpdateCartHandler() {
        super(UpdateCartPayLoad.class);
    }

    @Override
    protected DefaultResponseData handle(UpdateCartPayLoad request) throws Exception {
//        RequestValidationHelper.validateRequest(request);
        DefaultResponseData response = new DefaultResponseData();
        CartService.update(request);
        I18n.load(Global.locale);
        String message = I18n.get("updateCartSuccess");
        response.setMessage(message);
        return response;
    }
}
