package com.tttn.api.dbresult;

public class RGetStorage extends DBResult {
    public RGetStorage() {
        addField("id", DataType.INTEGER, 0);
        addField("title", DataType.STRING, "");
        addField("value", DataType.BIGDECIMAL, 0);
        addField("isUsed", DataType.BOOLEAN, "");

    }

    public Integer getId() {
        return (Integer) this.getValue("id");
    }
}
