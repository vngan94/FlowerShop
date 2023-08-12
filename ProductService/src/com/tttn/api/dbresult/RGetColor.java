package com.tttn.api.dbresult;

public class RGetColor extends DBResult{

	public RGetColor() {
		addField("id", DataType.INTEGER, 0);
		addField("name", DataType.STRING, "");
	}

	public Integer getId() {
		return (Integer) this.getValue("id");
	}

	public String getName() {
		return (String) this.getValue("name");
	}
}
