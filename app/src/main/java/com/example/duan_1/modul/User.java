package com.example.duan_1.modul;

public class User {

    private int mauser;
    private String username;
    private String name;
    private String phone;
    private String pass;
    private String address;
    private int role;//1 la admin 2la nguoi  dung

    public User() {
    }

    public User(int mauser, String username, String name, String phone, String pass, String address, int role) {
        this.mauser = mauser;
        this.username = username;
        this.name = name;
        this.phone = phone;
        this.pass = pass;
        this.address = address;
        this.role = role;
    }

    public int getMauser() {
        return mauser;
    }

    public void setMauser(int mauser) {
        this.mauser = mauser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
