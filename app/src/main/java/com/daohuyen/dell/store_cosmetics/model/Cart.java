package com.daohuyen.dell.store_cosmetics.model;

import java.util.ArrayList;
import java.util.Date;

public class Cart {
    private ArrayList<Item> items=new ArrayList<>();
    private int amount;


    public Cart() {

    }

    public Cart( ArrayList<Item> items,int amount) {
        this.items = items;
        this.amount=amount;
    }


    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void addItem(Item item){

            for (int i = 0; i <items.size() ; i++) {
                if(items.get(i).getProduct().getId().equals(item.getProduct().getId())){
                    items.get(i).setNumber(items.get(i).getNumber()+item.getNumber());
                    return;
                }

            }    items.add(item);

    }
    public void subItem(Item item){
        for (int i = 0; i < items.size(); i++) {
            if(items.get(i).getProduct().getId().equals(item.getProduct().getId())){
                items.remove(item);
            }

        }

    }
    public void subToCart(Item item) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).equals(item)) {
                if (items.get(i).getNumber() > 1) {
                    items.get(i).setNumber(items.get(i).getNumber() - 1);
                    return;
                } else {
                    items.remove(i);
                }
            }
        }
    }
    public void plusToCart(Item item){
        for (int i = 0; i < items.size(); i++) {
            if(items.get(i).equals(item)){
                items.get(i).setNumber(items.get(i).getNumber()+1);
                return;
            }
        }
        items.add(item);
    }


}
