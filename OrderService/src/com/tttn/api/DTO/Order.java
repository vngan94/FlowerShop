package com.tttn.api.DTO;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;


public class Order {

  private int id;
  private int transactionID;
  private int idFlower;
  private int  quantity;
  private BigDecimal amount;
  @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
  private Date created;
  @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
  private Date updated;
  private boolean isCanceled;



  public Order() {
  }

  public Order(int idFlower, int quantity, BigDecimal amount) {
    this.idFlower = idFlower;
    this.quantity = quantity;
    this.amount = amount;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

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

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public Date getUpdated() {
    return updated;
  }

  public void setUpdated(Date updated) {
    this.updated = updated;
  }

  public boolean isCanceled() {
    return isCanceled;
  }

  public void setCanceled(boolean canceled) {
    isCanceled = canceled;
  }
}
