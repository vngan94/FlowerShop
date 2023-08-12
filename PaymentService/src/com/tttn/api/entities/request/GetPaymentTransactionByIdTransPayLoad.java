package com.tttn.api.entities.request;

import com.tttn.api.entities.APIRequest;

public class GetPaymentTransactionByIdTransPayLoad extends APIRequest {
    private int idTrans;

    public int getIdTrans() {
        return idTrans;
    }

    public void setIdTrans(int idTrans) {
        this.idTrans = idTrans;
    }


}
