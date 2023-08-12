package com.tttn.flowershop.model;

import java.math.BigDecimal;


public class ShopCart {
	
	private int id;
	private int userID;
	private int idFlower;
	private int quantity;
	private BigDecimal amount;
	private boolean isOrdered;
	private boolean isDeleted;

	private boolean isSelected = true;

	private Flower flower;
	public ShopCart() {
	}

	public ShopCart(int id, int userID, int idFlower, int quantity, BigDecimal amount, boolean isOrdered) {
		this.id = id;
		this.userID = userID;
		this.idFlower = idFlower;
		this.quantity = quantity;
		this.amount = amount;
		this.isOrdered = isOrdered;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
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

	public boolean getIsOrdered() {
		return isOrdered;
	}

	public void setIsOrdered(boolean ordered) {
		isOrdered = ordered;
	}

	public boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean deleted) {
		isDeleted = deleted;
	}

	public Flower getFlower() {
		return flower;
	}

	public void setFlower(Flower flower) {
		this.flower = flower;
	}

	public boolean getIsSelected() {
		return isSelected;
	}

	public void seiIsSelected(boolean selected) {
		isSelected = selected;
	}
}
