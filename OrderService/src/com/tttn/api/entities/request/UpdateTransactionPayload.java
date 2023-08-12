package com.tttn.api.entities.request;

import com.tttn.api.entities.APIRequest;

public class UpdateTransactionPayload extends APIRequest {
    private int id;

    public UpdateTransactionPayload() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
