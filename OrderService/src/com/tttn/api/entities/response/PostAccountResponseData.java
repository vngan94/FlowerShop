package com.tttn.api.entities.response;

import com.tttn.api.entities.ResponseData;

public class PostAccountResponseData extends ResponseData {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
