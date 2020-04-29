package com.example.myapplication;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Product {
    @PrimaryKey(autoGenerate = true)
    private int uid;
    private String name;
    private int price;
    private Boolean isDigital;

    public Product(int uid, String name, int price, Boolean isDigital) {
        this.uid = uid;
        this.name = name;
        this.price = price;
        this.isDigital = isDigital;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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

    public Boolean getDigital() {
        return isDigital;
    }

    public void setDigital(Boolean digital) {
        isDigital = digital;
    }

    @Override
    public String toString() {
        return "Product{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", isDigital=" + isDigital +
                '}';
    }
}

