package com.tttn.api.handlers.write;

import com.tttn.api.entities.request.PostStoragePayLoad;
import com.tttn.api.entities.response.DefaultResponseData;
import com.tttn.api.handlers.APIHandler;
import com.tttn.api.resources.I18n;
import com.tttn.api.service.FlowerService;
import com.tttn.api.utils.Global;


public class PostStorageHandler extends APIHandler<PostStoragePayLoad, DefaultResponseData> {

    public PostStorageHandler() {
        super(PostStoragePayLoad.class);
    }

    @Override
    protected DefaultResponseData handle(PostStoragePayLoad request) throws Exception {
        Integer storageIdAdd = FlowerService.addStorage(request);
        DefaultResponseData response = new DefaultResponseData();
        response.setData(storageIdAdd);
        I18n.load(Global.locale);
        String message = I18n.get("addStorageSuccess");
        response.setMessage(message);
        return response;
    }
}
