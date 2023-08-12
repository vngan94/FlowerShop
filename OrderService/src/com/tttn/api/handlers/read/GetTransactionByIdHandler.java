package com.tttn.api.handlers.read;

import com.tttn.api.dbresult.RGetTransaction;
import com.tttn.api.entities.request.GetTransactionByIdPayLoad;
import com.tttn.api.entities.response.DefaultResponseData;
import com.tttn.api.handlers.APIHandler;
import com.tttn.api.resources.I18n;
import com.tttn.api.service.TransService;
import com.tttn.api.utils.Global;

public class GetTransactionByIdHandler extends APIHandler<GetTransactionByIdPayLoad, DefaultResponseData> {
    public GetTransactionByIdHandler() {
        super(GetTransactionByIdPayLoad.class);
    }


    @Override
    protected DefaultResponseData handle(GetTransactionByIdPayLoad request) throws Exception {
        DefaultResponseData response = new DefaultResponseData();
        RGetTransaction rGetTransaction = TransService.getTransactionById(request);
        response.setData(rGetTransaction != null ? rGetTransaction.getValueMap() : null);
        I18n.load(Global.locale);
        String message = I18n.get("getTransactionSuccess");
        response.setMessage(message);
        return response;
    }

}
