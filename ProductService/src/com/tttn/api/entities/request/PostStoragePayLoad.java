package com.tttn.api.entities.request;

import com.tttn.api.entities.APIRequest;

import java.math.BigDecimal;

public class PostStoragePayLoad extends APIRequest {

    private Integer idFlower;
    private Integer quantity_in;
    private String detail;
    private BigDecimal price;

    public Integer getIdFlower() {
        return idFlower;
    }

    public void setIdFlower(Integer idFlower) {
        this.idFlower = idFlower;
    }

    public Integer getQuantity_in() {
        return quantity_in;
    }

    public void setQuantity_in(Integer quantity_in) {
        this.quantity_in = quantity_in;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "PostStoragePayLoad{" +
                "idFlower=" + idFlower +
                ", quantity_in=" + quantity_in +
                ", detail='" + detail + '\'' +
                ", price=" + price +
                '}';
    }
}
