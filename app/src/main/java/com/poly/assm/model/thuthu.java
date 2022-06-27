package com.poly.assm.model;

public class thuthu {
    public String matt;
    public String hoten;
    public String matkhau;
    public thuthu(){

    }
    public thuthu(String matt, String hoten, String matkhau) {
        this.matt = matt;
        this.hoten = hoten;
        this.matkhau = matkhau;
    }

    public String getMatt() {
        return matt;
    }

    public void setMatt(String matt) {
        this.matt = matt;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }
}
