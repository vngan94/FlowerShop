package com.tttn.api.handlers.read;

import com.tttn.api.dbresult.RGetFlower;
import com.tttn.api.dbresult.RGetStorage;
import com.tttn.api.entities.request.GetListFlowerByKeyPayLoad;
import com.tttn.api.entities.request.GetStorageByFlowerPayLoad;
import com.tttn.api.entities.response.DefaultResponseData;
import com.tttn.api.handlers.APIHandler;
import com.tttn.api.resources.I18n;
import com.tttn.api.service.FlowerService;
import com.tttn.api.service.StorageService;
import com.tttn.api.utils.Global;

public class GetStorageByFlowerHandler extends APIHandler<GetStorageByFlowerPayLoad, DefaultResponseData> {
    public GetStorageByFlowerHandler() {
        super(GetStorageByFlowerPayLoad.class);
    }

    @Override
    protected DefaultResponseData handle(GetStorageByFlowerPayLoad request) throws Exception {
        DefaultResponseData response = new DefaultResponseData();

        int rGetStorage = StorageService.getStorageByFlower(request);
        response.setData(rGetStorage);
        I18n.load(Global.locale);
        String message = I18n.get("getStorageSuccess");
        response.setMessage(message);
        return response;
    }
}
