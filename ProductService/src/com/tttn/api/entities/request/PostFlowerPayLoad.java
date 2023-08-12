package com.tttn.api.entities.request;

import com.tttn.api.entities.APIRequest;

import java.math.BigDecimal;

public class PostFlowerPayLoad extends APIRequest {

    private String name;
    private BigDecimal price;
    private String contents;
    private Integer discount;
    private String listImage;
    private String listColor;
    private String listCategory;
    private Integer quantity_in;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getListImage() {
        return listImage;
    }

    public void setListImage(String listImage) {
        this.listImage = listImage;
    }

    public String getListColor() {
        return listColor;
    }

    public void setListColor(String listColor) {
        this.listColor = listColor;
    }

    public String getListCategory() {
        return listCategory;
    }

    public void setListCategory(String listCategory) {
        this.listCategory = listCategory;
    }

    public Integer getQuantity_in() {
        return quantity_in;
    }

    public void setQuantity_in(Integer quantity_in) {
        this.quantity_in = quantity_in;
    }
}
