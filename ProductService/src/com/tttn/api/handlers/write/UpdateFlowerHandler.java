package com.tttn.api.handlers.write;

import com.tttn.api.entities.request.PostFlowerPayLoad;
import com.tttn.api.entities.request.UpdateFlowerPayLoad;
import com.tttn.api.entities.response.DefaultResponseData;
import com.tttn.api.handlers.APIHandler;
import com.tttn.api.resources.I18n;
import com.tttn.api.service.FlowerService;
import com.tttn.api.utils.Global;

public class UpdateFlowerHandler extends APIHandler <UpdateFlowerPayLoad, DefaultResponseData> {

    public UpdateFlowerHandler() {
        super(UpdateFlowerPayLoad.class);
    }

    @Override
    protected DefaultResponseData handle(UpdateFlowerPayLoad request) throws Exception {

        Integer flowerIdUpdate = FlowerService.updateFlower(request);
        DefaultResponseData response = new DefaultResponseData();
        response.setData(flowerIdUpdate);
        I18n.load(Global.locale);
        String message = I18n.get("updateFlowerSuccess");
        response.setMessage(message);
        return response;

    }
}
