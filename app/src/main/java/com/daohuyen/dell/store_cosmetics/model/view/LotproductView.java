package com.daohuyen.dell.store_cosmetics.model.view;


import com.daohuyen.dell.store_cosmetics.model.response.LotProduct;

import java.io.Serializable;

public class LotproductView implements Serializable{
    private String id;
    private String name;
    private int price;
    private String logoUrl;
    private int amount;

    public LotproductView() {
    }

    public LotproductView(LotProduct lotProduct) {
        this.id = lotProduct.getId();
        this.name = lotProduct.getProduct().getName();
        this.price = lotProduct.getProduct().getPrice();
        this.logoUrl = lotProduct.getProduct().getLogoUrl();
        this.amount=lotProduct.getAmount();
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
}
