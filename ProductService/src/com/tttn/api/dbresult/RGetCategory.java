package com.tttn.api.dbresult;

public class RGetCategory extends DBResult{

	public RGetCategory() {
		addField("id", DataType.INTEGER, 0);
		addField("name", DataType.STRING, "");
		addField("parent_id", DataType.INTEGER, null);
	}

	public Integer getId() {
		return (Integer) this.getValue("id");
	}

	public String getName() {
		return (String) this.getValue("name");
	}
	public Integer getParentId() {
		return (Integer) this.getValue("parent_id");
	}
}
