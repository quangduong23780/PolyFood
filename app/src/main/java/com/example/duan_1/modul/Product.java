
package com.example.duan_1.modul;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Product implements Serializable {
    private int idproduct;
    private String nameproduct;
    private String typeproduct;
    private int priceproduct;
    private String imgproduct;
    private int soluongmua;

    public Product(int idproduct, String nameproduct, String typeproduct, int priceproduct, String imgproduct) {
        this.idproduct = idproduct;
        this.nameproduct = nameproduct;
        this.typeproduct = typeproduct;
        this.priceproduct = priceproduct;
        this.imgproduct = imgproduct;
    }

    public Product(int idproduct, String nameproduct, int soluongmua) {
        this.idproduct = idproduct;
        this.nameproduct = nameproduct;
        this.soluongmua = soluongmua;
    }

    public Product() {

    }

    public int getSoluongmua() {
        return soluongmua;
    }

    public void setSoluongmua(int soluongmua) {
        this.soluongmua = soluongmua;
    }

    public int getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(int idproduct) {
        this.idproduct = idproduct;
    }

    public String getNameproduct() {
        return nameproduct;
    }

    public void setNameproduct(String nameproduct) {
        this.nameproduct = nameproduct;
    }

    public String getTypeproduct() {
        return typeproduct;
    }

    public void setTypeproduct(String typeproduct) {
        this.typeproduct = typeproduct;
    }

    public int getPriceproduct() {
        return priceproduct;
    }

    public void setPriceproduct(int priceproduct) {
        this.priceproduct = priceproduct;
    }

    public String getImgproduct() {
        return imgproduct;
    }

    public void setImgproduct(String imgproduct) {
        this.imgproduct = imgproduct;
    }
}