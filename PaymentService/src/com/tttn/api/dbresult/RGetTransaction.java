package com.tttn.api.dbresult;

public class RGetTransaction extends DBResult {
    public RGetTransaction() {
        addField("id", DataType.INTEGER, 0);
        addField("amount", DataType.BIGDECIMAL, 0);
        addField("created", DataType.TIMESTAMP, "");
        addField("recipientAddress", DataType.STRING, 0);
        addField("recipientName", DataType.STRING, 0);
        addField("recipientPhone", DataType.STRING, 0);
        addField("tagName", DataType.STRING, "");
        addField("fullName", DataType.STRING, "");
        addField("phone", DataType.STRING, "");
    }
    public Integer getId() {
        return (Integer) this.getValue("id");
    }
}
