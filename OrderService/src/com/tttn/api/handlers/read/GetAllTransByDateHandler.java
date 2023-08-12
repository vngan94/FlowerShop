package com.tttn.api.handlers.read;

import com.tttn.api.dbresult.RGetAllTransactionByDate;
import com.tttn.api.dbresult.RGetTransaction;
import com.tttn.api.entities.request.GetAllTransactionByDatePayLoad;
import com.tttn.api.entities.response.DefaultResponseData;
import com.tttn.api.handlers.APIHandler;
import com.tttn.api.resources.I18n;
import com.tttn.api.service.TransService;
import com.tttn.api.utils.Global;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GetAllTransByDateHandler extends APIHandler<GetAllTransactionByDatePayLoad, DefaultResponseData> {
    public GetAllTransByDateHandler() {
        super(GetAllTransactionByDatePayLoad.class);
    }

    @Override
    protected DefaultResponseData handle(GetAllTransactionByDatePayLoad request) throws Exception {
        DefaultResponseData response = new DefaultResponseData();

        List<RGetAllTransactionByDate> listQuery = new ArrayList<>();
        listQuery = TransService.getAllTransactionByDate(request);
        // chuyen thanh json
        List<Map<String,Object>> data = listQuery.stream().map(RGetAllTransactionByDate::getValueMap).collect(Collectors.toList());
        // set data cho response
        response.setData(data);
        I18n.load(Global.locale);
        String message = I18n.get("getAllTransactionSuccess");
        response.setMessage(message);

        return response;
    }
}
