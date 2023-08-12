package com.tttn.api.entities.request;

import com.tttn.api.entities.APIRequest;

import java.math.BigDecimal;

public class UpdateFlowerPayLoad extends APIRequest {
    private String name;
    private BigDecimal price;
    private String contents;
    private Integer discount;
    private String listImage;
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UpdateFlowerPayLoad() {
    }

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

    @Override
    public String toString() {
        return "UpdateFlowerPayLoad{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", contents='" + contents + '\'' +
                ", discount=" + discount +
                ", listImage='" + listImage + '\'' +
                ", id=" + id +
                '}';
    }
}
