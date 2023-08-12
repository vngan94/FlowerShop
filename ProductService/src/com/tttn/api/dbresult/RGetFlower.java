package com.tttn.api.dbresult;

import java.math.BigDecimal;

public class RGetFlower extends DBResult{

	public RGetFlower() {
		addField("id", DataType.INTEGER, -1);
		addField("name", DataType.STRING, "");
		addField("price", DataType.BIGDECIMAL, 0);
		addField("contents", DataType.STRING, "");
		addField("quantity", DataType.INTEGER, 0);
		addField("discount", DataType.INTEGER, 0);
		addField("imagesName", DataType.STRING, "");
		addField("views", DataType.INTEGER, 0);
		addField("colorsName", DataType.STRING, "");
		addField("categoriesName", DataType.STRING, "");
	}

	public Integer getId() {
		return (Integer) this.getValue("id");
	}

	public String getName() {
		return (String) this.getValue("name");
	}

	public BigDecimal getPrice() {
		return (BigDecimal) this.getValue("price");
	}

	public String getContents() {
		return (String) this.getValue("contents");
	}

	public Integer getQuantity() {
		return (Integer) this.getValue("quantity");
	}

	public Integer getDiscount() {
		return (Integer) this.getValue("discount");
	}

	public String getImagesName() {
		return (String) this.getValue("imagesName");
	}

	public String getColorsName() {
		return (String) this.getValue("colorsName");
	}

	public String getCategoriesName() {
		return (String) this.getValue("categoriesName");
	}

	public Integer getViews() {
		return (Integer) this.getValue("views");
	}

}
