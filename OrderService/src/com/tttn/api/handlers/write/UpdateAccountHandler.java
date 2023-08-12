package com.tttn.api.handlers.write;

import com.tttn.api.entities.request.UpdateAccountPayLoad;
import com.tttn.api.entities.response.DefaultResponseData;
import com.tttn.api.handlers.APIHandler;
import com.tttn.api.resources.I18n;
import com.tttn.api.service.AccountService;
import com.tttn.api.utils.Global;

import java.util.TimeZone;

public class UpdateAccountHandler extends APIHandler<UpdateAccountPayLoad, DefaultResponseData> {

    static {
        System.setProperty("file.encoding", "UTF-8");
        TimeZone.setDefault(TimeZone.getTimeZone(System.getProperty("time.zone", "Asia/Ho_Chi_Minh")));
    }

    public UpdateAccountHandler() {
        super(UpdateAccountPayLoad.class);
    }

    @Override
    protected DefaultResponseData handle(UpdateAccountPayLoad request) throws Exception {
//        RequestValidationHelper.validateRequest(request);
        DefaultResponseData response = new DefaultResponseData();
        AccountService.updateAccount(request);
        I18n.load(Global.locale);
        String message = I18n.get("updateAccountSuccess");
        response.setMessage(message);
        return response;
    }
}
