package com.tttn.flowershop.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.NotBlank;

import com.tttn.flowershop.utils.Const;
import org.springframework.format.annotation.DateTimeFormat;

public class Transaction {
	private int id;

	private int userID;

	
	private String phone;
	 
	private String fullName;
	
	private String tagName;
		

	@NotBlank(message = "Recipient's Name cannot be blank")
	private String recipientName;

	@NotBlank(message = "Recipient's Email cannot be blank")
	@Email(message = "Email invalidate")
	private String recipientEmail;

	@NotBlank(message = "Phone Number cannot be blank")
	@Pattern(regexp = Const.Regex.regPhone,message = "PhoneNumber is not illegal")
	private String recipientPhone;

	@NotBlank(message = "Recipient's Address cannot be blank")
	private String recipientAddress;
	private BigDecimal amount;
	@NotBlank
	private String message;

	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date created;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date updated;

	private boolean status;

	private String note;

	private boolean isCanceled;

	private List<Order> orderList;
	
	
	
	private PaymentTransaction paymentTransaction;

	
	
	
	

	public PaymentTransaction getPaymentTransaction() {
		return paymentTransaction;
	}

	public void setPaymentTransaction(PaymentTransaction paymentTransaction) {
		this.paymentTransaction = paymentTransaction;
	}

	public Transaction() {
		super();
	}

		public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getTagName() {
		return tagName;
	}

	public void setStatusNew(String tagName) {
		this.tagName = tagName;
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

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getRecipientEmail() {
		return recipientEmail;
	}

	public void setRecipientEmail(String recipientEmail) {
		this.recipientEmail = recipientEmail;
	}

	public String getRecipientPhone() {
		return recipientPhone;
	}

	public void setRecipientPhone(String recipientPhone) {
		this.recipientPhone = recipientPhone;
	}

	public String getRecipientAddress() {
		return recipientAddress;
	}

	public void setRecipientAddress(String recipientAddress) {
		this.recipientAddress = recipientAddress;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public boolean isCanceled() {
		return isCanceled;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public void setCanceled(boolean canceled) {
		isCanceled = canceled;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}
}
