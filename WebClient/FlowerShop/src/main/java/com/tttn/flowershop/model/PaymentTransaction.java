package com.tttn.flowershop.model;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class PaymentTransaction {

	private int id;
	private int idTrans;
	private BigDecimal pointUsed;
	private int idGiftVoucher;
	private BigDecimal amount;
	private boolean status;
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date created;
	private int idPaymentType;
	private String paymentType;
	private BigDecimal giftVoucherValue;

	public PaymentTransaction() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdTrans() {
		return idTrans;
	}

	public void setIdTrans(int idTrans) {
		this.idTrans = idTrans;
	}

	public BigDecimal getPointUsed() {
		return pointUsed;
	}

	public void setPointUsed(BigDecimal pointUsed) {
		this.pointUsed = pointUsed;
	}

	public int getIdGiftVoucher() {
		return idGiftVoucher;
	}

	public void setIdGiftVoucher(int idGiftVoucher) {
		this.idGiftVoucher = idGiftVoucher;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public int getIdPaymentType() {
		return idPaymentType;
	}

	public void setIdPaymentType(int idPaymentType) {
		this.idPaymentType = idPaymentType;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public BigDecimal getGiftVoucherValue() {
		return giftVoucherValue;
	}

	public void setGiftVoucherValue(BigDecimal giftVoucherValue) {
		this.giftVoucherValue = giftVoucherValue;
	}

	@Override
	public String toString() {
		return "PaymentTransaction [id=" + id + ", idTrans=" + idTrans + ", pointUsed=" + pointUsed + ", idGiftVoucher="
				+ idGiftVoucher + ", amount=" + amount + ", status=" + status + ", created=" + created
				+ ", idPaymentType=" + idPaymentType + ", paymentType=" + paymentType + ", giftVoucherValue="
				+ giftVoucherValue + "]";
	}
	
	
	
	

}
