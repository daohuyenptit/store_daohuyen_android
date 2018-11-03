package com.daohuyen.dell.store_cosmetics.model;

import com.daohuyen.dell.store_cosmetics.common.Utils;

import java.io.Serializable;

public class Transport implements Serializable {
    private String name;
    private int price;
    private String time;

    public Transport() {
    }

    public Transport(String name, int price,String time) {
        this.name = name;
        this.price = price;
        this.time=time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    @Override
    public String toString() {
        return new String(name +"    "+ Utils.formatNumberMoney(price)+" Đ"+"\n"+time);
    }
}
