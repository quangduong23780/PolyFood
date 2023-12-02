package com.example.duan_1.modul;

public class HoaDon {
    int id;
    int masp;
    int price;
    int soluong;

    public HoaDon() {
    }

    public HoaDon(int id, int masp, int price, int soluong) {
        this.id = id;
        this.masp = masp;
        this.price = price;
        this.soluong = soluong;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}

