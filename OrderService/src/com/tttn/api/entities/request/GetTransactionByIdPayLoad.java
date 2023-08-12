package com.tttn.api.entities.request;

import com.tttn.api.entities.APIRequest;

public class GetTransactionByIdPayLoad extends APIRequest {
    private int id;

    public GetTransactionByIdPayLoad() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
