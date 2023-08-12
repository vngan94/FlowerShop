package com.tttn.api.handlers.write;

import com.tttn.api.entities.request.PostPaymentTransactionPayLoad;
import com.tttn.api.entities.response.DefaultResponseData;
import com.tttn.api.handlers.APIHandler;
import com.tttn.api.resources.I18n;
import com.tttn.api.service.PaymentTransactionService;
import com.tttn.api.utils.Global;

public class PostPaymentTransactionHandler extends APIHandler<PostPaymentTransactionPayLoad, DefaultResponseData> {

    public PostPaymentTransactionHandler() {
        super(PostPaymentTransactionPayLoad.class);
    }

    @Override
    protected DefaultResponseData handle(PostPaymentTransactionPayLoad request) throws Exception {
        System.out.println(request.toString());
        DefaultResponseData response = new DefaultResponseData();
        PaymentTransactionService.create(request);
        response.setData(1);
        I18n.load(Global.locale);
        String message = I18n.get("postPaymentTransactionSuccess");
        response.setMessage(message);
        return response;
    }
}
