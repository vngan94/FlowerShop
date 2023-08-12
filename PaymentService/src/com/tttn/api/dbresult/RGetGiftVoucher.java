package com.tttn.api.dbresult;

public class RGetGiftVoucher extends DBResult{

	public RGetGiftVoucher() {
		addField("id", DataType.INTEGER, 0);
		addField("title", DataType.STRING, "");
		addField("value", DataType.BIGDECIMAL, 0);
		addField("isUsed", DataType.BOOLEAN, null);

	}

	public Integer getId() {
			return (Integer) this.getValue("id");
	}
}
