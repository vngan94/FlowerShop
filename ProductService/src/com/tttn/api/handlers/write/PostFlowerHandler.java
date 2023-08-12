package com.tttn.api.handlers.write;

import com.tttn.api.entities.request.PostFlowerPayLoad;
import com.tttn.api.entities.response.DefaultResponseData;
import com.tttn.api.handlers.APIHandler;
import com.tttn.api.resources.I18n;
import com.tttn.api.service.FlowerService;
import com.tttn.api.utils.Global;


public class PostFlowerHandler extends APIHandler<PostFlowerPayLoad, DefaultResponseData> {

    public PostFlowerHandler() {
        super(PostFlowerPayLoad.class);
    }

    @Override
    protected DefaultResponseData handle(PostFlowerPayLoad request) throws Exception {
        Integer flowerIdAdd = FlowerService.addFlower(request);
        DefaultResponseData response = new DefaultResponseData();
        response.setData(flowerIdAdd);
        I18n.load(Global.locale);
        String message = I18n.get("postFlowerSuccess");
        response.setMessage(message);
        return response;
    }
}
