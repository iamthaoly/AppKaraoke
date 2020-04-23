package com.iamthaoly.model;

public class BaiHat {
    private String ma;
    private String ten;
    private String casy;
    private int thich;

    public BaiHat() {
    }

    public BaiHat(String ma, String ten, String casy, int thich) {
        this.ma = ma;
        this.ten = ten;
        this.casy = casy;
        this.thich = thich;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getCasy() {
        return casy;
    }

    public void setCasy(String casy) {
        this.casy = casy;
    }

    public int getThich() {
        return thich;
    }

    public void setThich(int thich) {
        this.thich = thich;
    }

}
