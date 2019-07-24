package com.springboot.project.onlineShop.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "product")
public class Product implements Serializable {

//    private static final String serialVersionUID = 5186013952828648626L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "product_category")
    private String product_category;

    @Column(name = "product_description")
    private String product_description;

    @Column(name = "product_manufacturer")
    private String product_manufacturer;

    @NotEmpty(message = "Product Name is mandatory")
    @Column(name = "product_name")
    private String product_name;

    @NotNull(message = "Please provide some price")
    @Column(name = "product_price")
    private double product_price;

    @Column(name = "unit_stock")
    private int unit_stock;

    @Transient
    private MultipartFile productImage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductCategory() {
        return product_category;
    }

    public void setProductCategory(String productCategory) {
        this.product_category = productCategory;
    }

    public String getProductDescription() {
        return product_description;
    }

    public void setProductDescription(String productDescription) {
        this.product_description = productDescription;
    }

    public String getProductManufacturer() {
        return product_manufacturer;
    }

    public void setProductManufacturer(String productManufacturer) {
        this.product_manufacturer = productManufacturer;
    }

    public String getProductName() {
        return product_name;
    }

    public void setProductName(String productName) {
        this.product_name = productName;
    }

    public double getProductPrice() {
        return product_price;
    }

    public void setProductPrice(double productPrice) {
        this.product_price = productPrice;
    }

    public int getUnitStock() {
        return unit_stock;
    }

    public void setUnitStock(int unitStock) {
        this.unit_stock = unitStock;
    }

    public MultipartFile getProductImage() {
        return productImage;
    }

    public void setProductImage(MultipartFile productImage) {
        this.productImage = productImage;
    }
}