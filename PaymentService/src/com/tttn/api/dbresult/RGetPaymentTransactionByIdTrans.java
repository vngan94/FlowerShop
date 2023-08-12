package com.tttn.api.dbresult;

public class RGetPaymentTransactionByIdTrans extends DBResult {
    public RGetPaymentTransactionByIdTrans () {
        addField("id", DataType.INTEGER, 0);
        addField("idTrans", DataType.INTEGER, 0);
        addField("pointUsed", DataType.BIGDECIMAL, 0);
        addField("idGiftVoucher", DataType.INTEGER, 0);
        addField("status", DataType.BOOLEAN, 0);
        addField("amount", DataType.BIGDECIMAL, 0);
        addField("created", DataType.TIMESTAMP, 0);
        addField("idPaymentType", DataType.INTEGER, 0);
        addField("paymentType", DataType.STRING, "");
        addField("giftVoucherValue", DataType.BIGDECIMAL, 0);
    }


    public Integer getId() {
        return (Integer) this.getValue("id");
    }
}
