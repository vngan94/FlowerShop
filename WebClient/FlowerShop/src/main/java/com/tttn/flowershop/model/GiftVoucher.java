package com.tttn.flowershop.model;

import java.math.BigDecimal;

public class GiftVoucher {

  private Integer id;
  private String title;
  private BigDecimal value;
  private Boolean isUsed;
  private Boolean isDeleted;


  public GiftVoucher() {
  }

  public GiftVoucher(String title, BigDecimal value) {
    this.title = title;
    this.value = value;
    
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public BigDecimal getValue() {
    return value;
  }

  public void setValue(BigDecimal value) {
    this.value = value;
  }

  public Boolean getIsUsed() {
    return isUsed;
  }

  public void setIsUsed(Boolean isUsed) {
    this.isUsed = isUsed;
  }



  public Boolean getIsDeleted() {
    return isDeleted;
  }

  public void setIsDeleted(Boolean isDeleted) {
    this.isDeleted = isDeleted;
  }

@Override
public String toString() {
	return "GiftVoucher [id=" + id + ", title=" + title + ", value=" + value + ", isUsed=" + isUsed + ", isDeleted="
			+ isDeleted + "]";
}
  
  
}
