package com.tttn.api.handlers.read;



import com.tttn.api.dbresult.RGetFlower;
import com.tttn.api.entities.APIRequest;
import com.tttn.api.entities.request.GetListFlowerByKeyPayLoad;
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

public class GetListFlowerByKeyHandler extends APIHandler<GetListFlowerByKeyPayLoad, DefaultResponseData> {

    static {
        System.setProperty("file.encoding", "UTF-8");
        TimeZone.setDefault(TimeZone.getTimeZone(System.getProperty("time.zone", "Asia/Ho_Chi_Minh")));
    }

    public GetListFlowerByKeyHandler() {
        super(GetListFlowerByKeyPayLoad.class);
    }

    @Override
    protected DefaultResponseData handle(GetListFlowerByKeyPayLoad request) throws Exception {
        DefaultResponseData response = new DefaultResponseData();

        List<RGetFlower> listQuery = new ArrayList<>();
        listQuery = FlowerService.getListFlowerByKey(request);
        List<Map<String,Object>> data = listQuery.stream().map(RGetFlower::getValueMap).collect(Collectors.toList());
        response.setData(data);
        I18n.load(Global.locale);
        String message = I18n.get("getListFlowerByKeySuccess");
        response.setMessage(message);
        return response;

    }
}
