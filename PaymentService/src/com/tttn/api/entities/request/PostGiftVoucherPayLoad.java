package com.tttn.api.entities.request;

import com.tttn.api.entities.APIRequest;


public class PostGiftVoucherPayLoad extends APIRequest {

    private String title;
    private String value;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}
