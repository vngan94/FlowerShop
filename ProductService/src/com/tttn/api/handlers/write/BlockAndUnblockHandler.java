package com.tttn.api.handlers.write;

import com.tttn.api.entities.request.BlockAndUnblockPayLoad;
import com.tttn.api.entities.request.UpdateFlowerPayLoad;
import com.tttn.api.entities.response.DefaultResponseData;
import com.tttn.api.handlers.APIHandler;
import com.tttn.api.resources.I18n;
import com.tttn.api.service.FlowerService;
import com.tttn.api.utils.Global;

public class BlockAndUnblockHandler extends APIHandler <BlockAndUnblockPayLoad, DefaultResponseData> {

    public BlockAndUnblockHandler() {
        super(BlockAndUnblockPayLoad.class);
    }

    @Override
    protected DefaultResponseData handle(BlockAndUnblockPayLoad request) throws Exception {
         FlowerService.blockAnUnblockFlower(request);
        DefaultResponseData response = new DefaultResponseData();
        I18n.load(Global.locale);
        response.setData(1);
        String message = I18n.get("blockFlowerSucess");
        response.setMessage(message);
        return response;
    }
}
