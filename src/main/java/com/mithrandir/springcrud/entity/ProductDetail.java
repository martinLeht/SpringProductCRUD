package com.mithrandir.springcrud.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "product_detail")
public class ProductDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@NotNull(message = "A descriptionis required")
	@Column(name = "description")
	private String description;

	@NotNull(message = "Category is required")
	@Column(name = "category")
	private String category;

	@DecimalMin(value = "0.001", inclusive = true, message = "Must a minimum of 0.001g")
	@Digits(integer = 7, fraction = 2, message = "Weight out of bounds, limit <7 digits>.<2 digits>")
	@Column(name = "weight_g")
	private float weight;

	@NotNull(message = "Manufacturer is required")
	@Column(name = "manufacturer")
	private String manufacturer;

	@NotNull(message = "Provide a country")
	@Column(name = "made_in_country")
	private String countryMadeIn;

	@OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id")
	private Product product;

	public ProductDetail() {

	}

	public ProductDetail(String description, String category, String manufacturer, String madeIn) {
		this.description = description;
		this.category = category;
		this.manufacturer = manufacturer;
		this.countryMadeIn = madeIn;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getCountryMadeIn() {
		return countryMadeIn;
	}

	public void setCountryMadeIn(String countryMadeIn) {
		this.countryMadeIn = countryMadeIn;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "ProductDetail [id=" + id + ", description=" + description + ", category=" + category + ", weight="
				+ weight + ", manufacturer=" + manufacturer + ", countryMadeIn=" + countryMadeIn + "]";
	}

}
