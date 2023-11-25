package com.example.duan_1.modul;

public class GioHang {
     private int id;
     private String nameproduct;
     private int priceproduct;
     private String imageproduct;
     private int soluong;


    public GioHang() {
    }

    public GioHang(int id, String nameproduct, int priceproduct, String imageproduct, int soluong) {
        this.id = id;
        this.nameproduct = nameproduct;
        this.priceproduct = priceproduct;
        this.imageproduct = imageproduct;
        this.soluong = soluong;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameproduct() {
        return nameproduct;
    }

    public void setNameproduct(String nameproduct) {
        this.nameproduct = nameproduct;
    }

    public int getPrirceproduct() {
        return priceproduct;
    }

    public void setPrirceproduct(int prirceproduct) {
        this.priceproduct = prirceproduct;
    }

    public String getImageproduct() {
        return imageproduct;
    }

    public void setImageproduct(String imageproduct) {
        this.imageproduct = imageproduct;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
