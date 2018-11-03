package com.daohuyen.dell.store_cosmetics.model;


import com.daohuyen.dell.store_cosmetics.model.response.Category;

import java.util.Date;


public class Product {
    public static final String CREATED_DATE = "createdDate";
    private String id;
    private String name;
    private int price;
    private String description;
    private Date createdDate;
    private String logoUrl;
    private Category category;

    public Product() {
    }

    public Product(String name, int price, String description, Date createDate, String logoUrl, Category category) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.logoUrl = logoUrl;
        this.category = category;
    }
    public void onPrepersist(){
        this.createdDate = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
