package com.tttn.api.entities.request;

import com.tttn.api.entities.APIRequest;

import java.math.BigDecimal;

public class PostPaymentTransactionPayLoad extends APIRequest {

    private int idTrans;
    private BigDecimal pointUsed;
    private int idGiftVoucher;
    private BigDecimal amount;
    private int idPaymentType;

    public PostPaymentTransactionPayLoad(int idTrans) {
        this.idTrans = idTrans;
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

    public int getIdPaymentType() {
        return idPaymentType;
    }

    public void setIdPaymentType(int idPaymentType) {
        this.idPaymentType = idPaymentType;
    }

    @Override
    public String toString() {
        return "PostPaymentTransactionPayLoad{" +
                "idTrans=" + idTrans +
                ", pointUsed=" + pointUsed +
                ", idGiftVoucher=" + idGiftVoucher +
                ", amount=" + amount +
                ", idPaymentType=" + idPaymentType +
                '}';
    }
}
