package com.daohuyen.dell.store_cosmetics.model.view;


import com.daohuyen.dell.store_cosmetics.model.Customer;

import java.util.Date;

public class CustomerView {
    private String id;
    private String account;
    private String password;
    private String fullName;
    private String phone;
    private String gender;
    private String address;
    private String identityCard;
    private String description;
    private String avatarUrl;
    private long birthday;
    private String email;
    public CustomerView() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public CustomerView(Customer customer) {
        this.id=customer.getId();
        this.fullName = customer.getFullName();
        this.phone = customer.getPhone();
        this.gender= customer.getGenderID().getName();
        this.avatarUrl=customer.getAvatarUrl();
        this.email=customer.getEmail();
        this.description=customer.getDescription();
        this.identityCard=customer.getIdentityCard();
        this.account=customer.getUser().getUsername();
        this.address=customer.getAddress();
        this.password=customer.getUser().getPassword();
        setBirthday(customer.getBirthday().getTime());
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
