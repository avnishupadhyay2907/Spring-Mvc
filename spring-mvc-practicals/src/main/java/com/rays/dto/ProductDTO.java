package com.rays.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "st_product")
public class ProductDTO {
	@Id
	@GeneratedValue(generator = "vanshaPk")
	@GenericGenerator(name = "vanshaPk", strategy = "native")
	@Column(name = "ID", unique = true, nullable = false)
	protected Long id = null;

	@Column(name = "product_name", length = 50)
	private String productName = null;

	@Column(name = "product_ammount", length = 50)
	private String productAmmount = null;

	@Column(name = "purchase_date", length = 50)
	private Date purchaseDate;

	@Column(name = "product_category", length = 50)
	private String productCategory = null;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductAmmount() {
		return productAmmount;
	}

	public void setProductAmmount(String productAmmount) {
		this.productAmmount = productAmmount;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	
}
