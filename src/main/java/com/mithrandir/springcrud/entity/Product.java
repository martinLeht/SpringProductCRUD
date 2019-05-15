package com.mithrandir.springcrud.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@NotNull(message = "Product name is required")
	@Size(min = 1, message = "is required")
	@Column(name = "product_name")
	private String productName;

	@DecimalMin(value = "0.01", inclusive = true, message = "Price must be a minimum of 0.01$")
	@Digits(integer = 6, fraction = 2, message = "Price out of bounds, limit <6 digits>.<2 digits>")
	@Column(name = "price")
	private float price;

	@NotNull(message = "Quantity is required")
	@Min(value = 1, message = "Must be greater than zero")
	@Column(name = "qty")
	private Integer quantity;

	@NotNull(message = "Email is required")
	@Email(message = "Provide a valid email address")
	@Pattern(regexp = ".+@.+\\..+", message = "Provide a valid email address")
	@Column(name = "added_by")
	private String addedBy;

	@Column(name = "creation_datetime")
	private Date createDateTime;

	@Column(name = "last_updated")
	private Date updateDateTime;

	@Valid
	@OneToOne(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private ProductDetail productDetail;

	@Column(name = "value_in_stock")
	private float valueInStock;

	public Product() {

	}

	public Product(String productName, float price, Integer quantity, String addedBy) {
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
		this.addedBy = addedBy;
		this.valueInStock = this.price * this.quantity;
	}

	public ProductDetail getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(ProductDetail productDetail) {
		if (productDetail == null) {
			if (this.productDetail != null) {
				this.productDetail.setProduct(null);
			}
		} else {
			productDetail.setProduct(this);
		}
		this.productDetail = productDetail;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}

	public float getValueInStock() {
		return valueInStock;
	}

	public void setValueInStock() {
		this.valueInStock = this.price * this.quantity;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public Date getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(Date updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName + ", price=" + price + ", quantity=" + quantity
				+ ", addedBy=" + addedBy + ", createDateTime=" + createDateTime + ", updateDateTime=" + updateDateTime
				+ ", productDetail=" + productDetail + ", valueInStock=" + valueInStock + "]";
	}

}
