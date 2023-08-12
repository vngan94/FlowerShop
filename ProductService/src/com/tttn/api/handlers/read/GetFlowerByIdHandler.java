package com.tttn.api.handlers.read;

import com.tttn.api.dbresult.RGetFlower;
import com.tttn.api.entities.request.GetFlowerByIdPayLoad;
import com.tttn.api.entities.response.DefaultResponseData;
import com.tttn.api.handlers.APIHandler;
import com.tttn.api.resources.I18n;
import com.tttn.api.service.FlowerService;
import com.tttn.api.utils.Global;

import java.util.TimeZone;

public class GetFlowerByIdHandler extends APIHandler<GetFlowerByIdPayLoad, DefaultResponseData> {

    static {
        System.setProperty("file.encoding", "UTF-8");
        TimeZone.setDefault(TimeZone.getTimeZone(System.getProperty("time.zone", "Asia/Ho_Chi_Minh")));
    }

    public GetFlowerByIdHandler() {
        super(GetFlowerByIdPayLoad.class);
    }

    @Override
    protected DefaultResponseData handle(GetFlowerByIdPayLoad request) throws Exception {
        DefaultResponseData response = new DefaultResponseData();

        RGetFlower flower = FlowerService.getFlowerById(request);
        response.setData(flower.getValueMap());
        I18n.load(Global.locale);
        String message = I18n.get("getFlowerSuccess");
        response.setMessage(message);
        return response;

    }
}
