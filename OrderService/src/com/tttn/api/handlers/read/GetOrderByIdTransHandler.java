package com.tttn.api.handlers.read;

import com.tttn.api.dbresult.RGetOrder;
import com.tttn.api.entities.request.GetAllTransactionByDatePayLoad;
import com.tttn.api.entities.request.GetTransactionByIdPayLoad;
import com.tttn.api.entities.response.DefaultResponseData;
import com.tttn.api.handlers.APIHandler;
import com.tttn.api.resources.I18n;
import com.tttn.api.service.OrderService;
import com.tttn.api.utils.Global;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GetOrderByIdTransHandler extends APIHandler <GetTransactionByIdPayLoad, DefaultResponseData> {

    public GetOrderByIdTransHandler() {
        super(GetTransactionByIdPayLoad.class);
    }

    @Override
    protected DefaultResponseData handle(GetTransactionByIdPayLoad request) throws Exception {
        DefaultResponseData response = new DefaultResponseData();
        List<RGetOrder> listQuery = OrderService.getOrderByIdTrans(request);
        List<Map<String,Object>> data = listQuery.stream().map(RGetOrder::getValueMap).collect(Collectors.toList());
        response.setData(data);
        I18n.load(Global.locale);
        String message = I18n.get("getOrderSuccess");
        response.setMessage(message);
        return response;
    }
}
