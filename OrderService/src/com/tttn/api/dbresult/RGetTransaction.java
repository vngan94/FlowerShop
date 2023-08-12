package com.tttn.api.dbresult;

public class RGetTransaction extends DBResult {
    public RGetTransaction() {
        addField("id", DataType.INTEGER, 0);
        addField("created", DataType.TIMESTAMP, "");
        addField("amount", DataType.BIGDECIMAL, 0);
        addField("phone", DataType.STRING, "");
        addField("fullName", DataType.STRING, "");
        addField("tagName", DataType.STRING, "");
        addField("recipientAddress", DataType.STRING, "");
        addField("recipientName", DataType.STRING, "");
        addField("recipientPhone", DataType.STRING, "");
    }

    public Integer getId() {
        return (Integer) this.getValue("id");
    }
}
