package com.tttn.api.handlers.write;

import com.tttn.api.entities.request.UpdateTransactionPayload;
import com.tttn.api.entities.response.DefaultResponseData;
import com.tttn.api.handlers.APIHandler;
import com.tttn.api.resources.I18n;
import com.tttn.api.service.TransService;
import com.tttn.api.utils.Global;

import java.util.TimeZone;

public class AcceptTransactionHandler extends APIHandler<UpdateTransactionPayload, DefaultResponseData> {
    static {
        System.setProperty("file.encoding", "UTF-8");
        TimeZone.setDefault(TimeZone.getTimeZone(System.getProperty("time.zone", "Asia/Ho_Chi_Minh")));
    }

    public AcceptTransactionHandler() {
        super(UpdateTransactionPayload.class);
    }

    @Override
    protected DefaultResponseData handle(UpdateTransactionPayload request) throws Exception {
        DefaultResponseData response = new DefaultResponseData();
        TransService.accept(request);
        I18n.load(Global.locale);
        String message = I18n.get("acceptTransactionSuccess");
        response.setMessage(message);
        response.setData(1);
        return response;
    }
}
