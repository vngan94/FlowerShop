package com.tttn.api.handlers.write;

import com.tttn.api.entities.request.PostOrderPayload;
import com.tttn.api.entities.request.UpdateTransactionPayload;
import com.tttn.api.entities.response.DefaultResponseData;
import com.tttn.api.handlers.APIHandler;
import com.tttn.api.resources.I18n;
import com.tttn.api.service.AccountService;
import com.tttn.api.service.OrderService;
import com.tttn.api.utils.Global;

public class PostOrderHandler extends APIHandler<PostOrderPayload, DefaultResponseData> {
    public PostOrderHandler() {
        super(PostOrderPayload.class);
    }

    @Override
    protected DefaultResponseData handle(PostOrderPayload request) throws Exception {

        DefaultResponseData response = new DefaultResponseData();
        OrderService.create(request);
        response.setData(1);
        I18n.load(Global.locale);
        String message = I18n.get("postOrderSuccess");
        response.setMessage(message);
        return response;
    }
}
