package com.tttn.api.dbresult;

public class RGetOrder extends  DBResult{
    public RGetOrder() {

        addField("idFlower", DataType.INTEGER, 0);
        addField("quantity", DataType.INTEGER, 0);
        addField("amount", DataType.BIGDECIMAL, 0);


    }
    public Integer getId() {
        return (Integer) this.getValue("id");
    }
}
