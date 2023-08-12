package com.tttn.api.dbresult;

public class RGetCart extends DBResult{

	public RGetCart() {
		addField("id", DataType.INTEGER, null);
		addField("userID", DataType.INTEGER,null);
		addField("idFlower", DataType.INTEGER, null);
		addField("quantity", DataType.INTEGER, null);
		addField("amount", DataType.BIGDECIMAL, null);
		addField("isOrdered", DataType.BOOLEAN, null);
		addField("isDeleted", DataType.BOOLEAN, null);
	}

	public Integer getId() {
		return (Integer) this.getValue("id");
	}

}
