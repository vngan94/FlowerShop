package com.tttn.api.handlers.read;

import com.tttn.api.dbresult.RGetGiftVoucher;
import com.tttn.api.dbresult.RGetPaymentTransactionByIdTrans;
import com.tttn.api.entities.request.GetPaymentTransactionByIdTransPayLoad;
import com.tttn.api.entities.response.DefaultResponseData;
import com.tttn.api.handlers.APIHandler;
import com.tttn.api.resources.I18n;
import com.tttn.api.service.PaymentTransactionService;
import com.tttn.api.utils.Global;

public class GetPaymentTransactionByIdTransHandler extends APIHandler<GetPaymentTransactionByIdTransPayLoad, DefaultResponseData> {
    public GetPaymentTransactionByIdTransHandler() {
        super(GetPaymentTransactionByIdTransPayLoad.class);
    }

    @Override
    protected DefaultResponseData handle(GetPaymentTransactionByIdTransPayLoad request) throws Exception {
        DefaultResponseData response = new DefaultResponseData();
        RGetPaymentTransactionByIdTrans rGetPaymentTransactionByIdTrans = PaymentTransactionService.getPaymentTransactionByIdTrans(request);
        response.setData(rGetPaymentTransactionByIdTrans != null ? rGetPaymentTransactionByIdTrans.getValueMap() : null);
        I18n.load(Global.locale);
        String message = I18n.get("getPaymentTransactionSuccess");
        response.setMessage(message);
        return response;
    }
}
