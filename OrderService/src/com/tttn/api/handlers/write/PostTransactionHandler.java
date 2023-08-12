package com.tttn.api.handlers.write;

import com.tttn.api.entities.request.PostCartPayLoad;
import com.tttn.api.entities.request.PostTransactionPayLoad;
import com.tttn.api.entities.response.DefaultResponseData;
import com.tttn.api.handlers.APIHandler;
import com.tttn.api.resources.I18n;
import com.tttn.api.service.CartService;
import com.tttn.api.service.TransService;
import com.tttn.api.utils.Global;

import java.util.TimeZone;

public class PostTransactionHandler extends APIHandler<PostTransactionPayLoad, DefaultResponseData> {

    static {
        System.setProperty("file.encoding", "UTF-8");
        TimeZone.setDefault(TimeZone.getTimeZone(System.getProperty("time.zone", "Asia/Ho_Chi_Minh")));
    }

    public PostTransactionHandler() {
        super(PostTransactionPayLoad.class);
    }

    @Override
    protected DefaultResponseData handle(PostTransactionPayLoad request) throws Exception {
//        RequestValidationHelper.validateRequest(request);
        DefaultResponseData response = new DefaultResponseData();
        Integer idTrans = TransService.create(request);
        response.setData(idTrans);
        I18n.load(Global.locale);
        String message = I18n.get("postCartSuccess");
        response.setMessage(message);
        return response;
    }
}
