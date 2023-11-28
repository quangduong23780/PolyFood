package com.example.duan_1.modul;

public class Donhang {
    int id;
    String tenkhachhang;
    int sodienthoai;
    String email;
    String diachi;

    public Donhang(int id, String tenkhachhang, int sodienthoai, String email, String diachi) {
        this.id = id;
        this.tenkhachhang = tenkhachhang;
        this.sodienthoai = sodienthoai;
        this.email = email;
        this.diachi = diachi;
    }

    public Donhang() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenkhachhang() {
        return tenkhachhang;
    }

    public void setTenkhachhang(String tenkhachhang) {
        this.tenkhachhang = tenkhachhang;
    }

    public int getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(int sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }
}
