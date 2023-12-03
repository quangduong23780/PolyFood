package com.example.duan_1.modul;

public class HoaDon {
    int id;
    int masp;
    int price;
    int soluong;
    int trangthai;
    String ngay;

    public HoaDon() {
    }

    public HoaDon(int id, int masp, int price, int soluong, int trangthai) {
        this.id = id;
        this.masp = masp;
        this.price = price;
        this.soluong = soluong;
        this.trangthai = trangthai;
    }

    public HoaDon(int id, int masp, int price, int soluong, int trangthai, String ngay) {
        this.id = id;
        this.masp = masp;
        this.price = price;
        this.soluong = soluong;
        this.trangthai = trangthai;
        this.ngay = ngay;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
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

