package com.tttn.api.handlers.read;

import com.tttn.api.dbresult.RGetOrder;
import com.tttn.api.dbresult.RGetTransaction;
import com.tttn.api.entities.request.GetAllTransactionByDatePayLoad;
import com.tttn.api.entities.request.GetTransactionByIdPayLoad;
import com.tttn.api.entities.response.DefaultResponseData;
import com.tttn.api.handlers.APIHandler;
import com.tttn.api.resources.I18n;
import com.tttn.api.service.OrderService;
import com.tttn.api.service.TransService;
import com.tttn.api.utils.Global;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GetAllOrderByIdTransReportHandler extends APIHandler<GetAllTransactionByDatePayLoad, DefaultResponseData> {

    public GetAllOrderByIdTransReportHandler() {
        super(GetAllTransactionByDatePayLoad.class);
    }

    @Override
    protected DefaultResponseData handle(GetAllTransactionByDatePayLoad request) throws Exception {
        DefaultResponseData response = new DefaultResponseData();

        List<RGetOrder> listQuery = new ArrayList<>();
        listQuery = OrderService.getAllOrderByIdTransReport(request);
        // chuyen thanh json
        List<Map<String,Object>> data = listQuery.stream().map(RGetOrder::getValueMap).collect(Collectors.toList());
        // set data cho response
        response.setData(data);
        I18n.load(Global.locale);
        String message = I18n.get("getAllOrderSuccess");
        response.setMessage(message);

        return response;

    }
}
