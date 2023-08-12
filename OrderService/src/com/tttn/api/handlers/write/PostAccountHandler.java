package com.tttn.api.handlers.write;

import com.tttn.api.entities.request.PostAccountPayLoad;
import com.tttn.api.entities.response.DefaultResponseData;
import com.tttn.api.handlers.APIHandler;
import com.tttn.api.resources.I18n;
import com.tttn.api.service.AccountService;
import com.tttn.api.utils.Global;

import java.util.TimeZone;

public class PostAccountHandler extends APIHandler<PostAccountPayLoad, DefaultResponseData> {

    static {
        System.setProperty("file.encoding", "UTF-8");
        TimeZone.setDefault(TimeZone.getTimeZone(System.getProperty("time.zone", "Asia/Ho_Chi_Minh")));
    }

    public PostAccountHandler() {
        super(PostAccountPayLoad.class);
    }

    @Override
    protected DefaultResponseData handle(PostAccountPayLoad request) throws Exception {
//        RequestValidationHelper.validateRequest(request);
        DefaultResponseData response = new DefaultResponseData();
        Integer userAdd = AccountService.createAccount(request);
        response.setData(userAdd);
        I18n.load(Global.locale);
        String message = I18n.get("postAccountSuccess");
        response.setMessage(message);
        return response;
    }
}
