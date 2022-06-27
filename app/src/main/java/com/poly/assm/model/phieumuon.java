package com.poly.assm.model;

import java.util.Date;

public class phieumuon {
    public int mapm;
    public int matv;
    public int masach;
    public int tienthue;
    public int trasach;
    public String ngay;

    public phieumuon(){
    }

    public phieumuon(int mapm, int matv, int masach, int tienthue, int trasach, String ngay) {
        this.mapm = mapm;
        this.matv = matv;
        this.masach = masach;
        this.tienthue = tienthue;
        this.trasach = trasach;
        this.ngay = ngay;
    }

    public int getMapm() {
        return mapm;
    }

    public void setMapm(int mapm) {
        this.mapm = mapm;
    }

    public int getMatv() {
        return matv;
    }

    public void setMatv(int matv) {
        this.matv = matv;
    }

    public int getMasach() {
        return masach;
    }

    public void setMasach(int masach) {
        this.masach = masach;
    }

    public int getTienthue() {
        return tienthue;
    }

    public void setTienthue(int tienthue) {
        this.tienthue = tienthue;
    }

    public int getTrasach() {
        return trasach;
    }

    public void setTrasach(int trasach) {
        this.trasach = trasach;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }
}
