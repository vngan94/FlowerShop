package com.tttn.api.entities.request;

import com.tttn.api.entities.APIRequest;

import java.math.BigDecimal;

public class PostCartPayLoad extends APIRequest {

    private Integer userId;
    private Integer idFlower;
    private Integer quantity;
    private BigDecimal amount;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getIdFlower() {
        return idFlower;
    }

    public void setIdFlower(Integer idFlower) {
        this.idFlower = idFlower;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
