package com.example.duan_1.modul;

public class Oder {
     private int idproduct;
     private String imgproduct;
     private String nameproduct;
     private int quantity;
     private int price;

    public Oder() {
    }

    public Oder(int idproduct, String imgproduct, String nameproduct, int quantity, int price) {
        this.idproduct = idproduct;
        this.imgproduct = imgproduct;
        this.nameproduct = nameproduct;
        this.quantity = quantity;
        this.price = price;
    }

    public int getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(int idproduct) {
        this.idproduct = idproduct;
    }

    public String getImgproduct() {
        return imgproduct;
    }

    public void setImgproduct(String imgproduct) {
        this.imgproduct = imgproduct;
    }

    public String getNameproduct() {
        return nameproduct;
    }

    public void setNameproduct(String nameproduct) {
        this.nameproduct = nameproduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
