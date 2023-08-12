package com.tttn.api.handlers.read;



import com.tttn.api.dbresult.RGetColor;
import com.tttn.api.entities.APIRequest;
import com.tttn.api.entities.response.DefaultResponseData;
import com.tttn.api.handlers.APIHandler;
import com.tttn.api.resources.I18n;
import com.tttn.api.service.FlowerService;
import com.tttn.api.utils.Global;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.stream.Collectors;

public class GetAllColorHandler extends APIHandler<APIRequest, DefaultResponseData> {

    static {
        System.setProperty("file.encoding", "UTF-8");
        TimeZone.setDefault(TimeZone.getTimeZone(System.getProperty("time.zone", "Asia/Ho_Chi_Minh")));
    }

    public GetAllColorHandler() {
        super(APIRequest.class);
    }

    @Override
    protected DefaultResponseData handle(APIRequest request) throws Exception {
        DefaultResponseData response = new DefaultResponseData();

        List<RGetColor> listQuery = new ArrayList<>();
        listQuery = FlowerService.getAllColor();
        List<Map<String,Object>> data = listQuery.stream().map(RGetColor::getValueMap).collect(Collectors.toList());
        response.setData(data);
        I18n.load(Global.locale);
        String message = I18n.get("getAllColorSuccess");
        response.setMessage(message);
        return response;

    }
}
