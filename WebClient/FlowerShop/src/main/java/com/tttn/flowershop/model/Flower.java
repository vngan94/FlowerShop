package com.tttn.flowershop.model;

import java.math.BigDecimal;
import java.util.List;

public class Flower {

	private int id;
	private String name;
	private BigDecimal price;
	private String contents;
	private Integer quantity;
	private Integer discount;
	private List<String> images;
	private Integer views;
	private List<String> colors;
	private List<String> categories;

	private String colorsName;
	private String categoriesName;
	private String imagesName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	

	

	public Integer getViews() {
		return views;
	}

	public void setViews(Integer views) {
		this.views = views;
	}

	public List<String> getColors() {
		return colors;
	}

	public void setColors(List<String> colors) {
		this.colors = colors;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public String getColorsName() {
		return colorsName;
	}

	public void setColorsName(String colorsName) {
		this.colorsName = colorsName;
	}

	public String getCategoriesName() {
		return categoriesName;
	}

	public void setCategoriesName(String categoriesName) {
		this.categoriesName = categoriesName;
	}

	public String getImagesName() {
		return imagesName;
	}

	public void setImagesName(String imagesName) {
		this.imagesName = imagesName;
	}

	public BigDecimal getPriceAfterDiscount(){
		return (this.getPrice().multiply(BigDecimal.valueOf(100 - this.getDiscount())).divide(BigDecimal.valueOf(100)));
	}

	@Override
	public String toString() {
		return "Flower [id=" + id + ", name=" + name + ", price=" + price + ", contents=" + contents + ", quantity="
				+ quantity + ", discount=" + discount + ", images=" + images + ", views=" + views + ", colors=" + colors
				+ ", categories=" + categories + ", colorsName=" + colorsName + ", categoriesName=" + categoriesName
				+ ", imagesName=" + imagesName + "]";
	}
	
}