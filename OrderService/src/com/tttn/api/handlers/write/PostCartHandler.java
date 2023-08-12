package com.tttn.api.handlers.write;

import com.tttn.api.entities.request.PostCartPayLoad;
import com.tttn.api.entities.response.DefaultResponseData;
import com.tttn.api.handlers.APIHandler;
import com.tttn.api.resources.I18n;
import com.tttn.api.service.CartService;
import com.tttn.api.utils.Global;

import java.util.TimeZone;

public class PostCartHandler extends APIHandler<PostCartPayLoad, DefaultResponseData> {

    static {
        System.setProperty("file.encoding", "UTF-8");
        TimeZone.setDefault(TimeZone.getTimeZone(System.getProperty("time.zone", "Asia/Ho_Chi_Minh")));
    }

    public PostCartHandler() {
        super(PostCartPayLoad.class);
    }

    @Override
    protected DefaultResponseData handle(PostCartPayLoad request) throws Exception {
//        RequestValidationHelper.validateRequest(request);
        DefaultResponseData response = new DefaultResponseData();
        Integer cartAdd = CartService.create(request);
        response.setData(cartAdd);
        I18n.load(Global.locale);
        String message = I18n.get("postCartSuccess");
        response.setMessage(message);
        return response;
    }
}
