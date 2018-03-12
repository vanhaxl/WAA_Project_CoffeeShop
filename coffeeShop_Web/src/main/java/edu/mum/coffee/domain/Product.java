package edu.mum.coffee.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class Product implements Serializable {
    private static final long serialVersionUID = 3678107792576131001L;

    @Id
    @GeneratedValue
    private int id;
    private String productName;
    private String description;
    private double price;
    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @Transient
    private MultipartFile productImage;

    public Product() {
        super(); // default constructor
    }

    public Product(String productName, String description, double price, ProductType productType) {
        super();
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.productType = productType;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public MultipartFile getProductImage() {
        return productImage;
    }

    public void setProductImage(MultipartFile productImage) {
        this.productImage = productImage;
    }

}
