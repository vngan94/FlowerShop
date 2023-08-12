package com.tttn.api.entities.request;

import com.tttn.api.entities.APIRequest;

import java.math.BigDecimal;

public class PostOrderPayload extends APIRequest {
    private int transactionID;
    private int idFlower;
    private int quantity;
    private BigDecimal amount;

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public int getIdFlower() {
        return idFlower;
    }

    public void setIdFlower(int idFlower) {
        this.idFlower = idFlower;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "PostOrderPayload{" +
                "transactionID=" + transactionID +
                ", idFlower=" + idFlower +
                ", quantity=" + quantity +
                ", amount=" + amount +
                '}';
    }
}
