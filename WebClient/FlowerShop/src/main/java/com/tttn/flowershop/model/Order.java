package com.tttn.flowershop.model;

import java.math.BigDecimal;

import org.springframework.format.annotation.DateTimeFormat;

public class Order {
	private int id;
	private int transactionID;
	private int idFlower;
	private int quantity;
	private BigDecimal amount;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private int created;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private int updated;
	
	private boolean canceled;
	
	private Flower flower;
	
	public Order(int idFlower, int quantity, BigDecimal amount, int transactionID) {
	    this.idFlower = idFlower;
	    this.quantity = quantity;
	    this.amount = amount;
	    this.transactionID = transactionID;
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

	public int getCreated() {
		return created;
	}

	public void setCreated(int created) {
		this.created = created;
	}

	public int getUpdated() {
		return updated;
	}

	public void setUpdated(int updated) {
		this.updated = updated;
	}

	
	public boolean isCanceled() {
		return canceled;
	}

	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}

	public Flower getFlower() {
		return flower;
	}

	public void setFlower(Flower flower) {
		this.flower = flower;
	}

	public Order() {
		super();
	}

	public Order(int id, int transactionID, int idFlower, int quantity, BigDecimal amount, int created, int updated,
			boolean isCanceled, Flower flower) {
		super();
		this.id = id;
		this.transactionID = transactionID;
		this.idFlower = idFlower;
		this.quantity = quantity;
		this.amount = amount;
		this.created = created;
		this.updated = updated;
		this.canceled = isCanceled;
		this.flower = flower;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", transactionID=" + transactionID + ", idFlower=" + idFlower + ", quantity="
				+ quantity + ", amount=" + amount + ", created=" + created + ", updated=" + updated + ", canceled="
				+ canceled + ", flower=" + flower + "]";
	}
	
	
	
	


}
