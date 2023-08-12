package com.tttn.flowershop.model;

import java.math.BigDecimal;

public class Storage {
  private Integer idFlower;
  private Integer quantity_in;
  private String detail;
  private BigDecimal price;
  private Integer quantity_remain;

  public Storage() {
  }

  public Storage(Integer idFlower, Integer quantity_in, String detail, BigDecimal price) {
    this.idFlower = idFlower;
    this.quantity_in = quantity_in;
    this.detail = detail;
    this.price = price;
  }

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

public Integer getQuantity_remain() {
	return quantity_remain;
}

public void setQuantity_remain(Integer quantity_remain) {
	this.quantity_remain = quantity_remain;
}

@Override
public String toString() {
	return "Storage [idFlower=" + idFlower + ", quantity_in=" + quantity_in + ", detail=" + detail + ", price=" + price
			+ ", quantity_remain=" + quantity_remain + "]";
}

  

  
}
