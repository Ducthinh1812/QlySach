package com.poly.assm.model;

public class thanhvien {
    public int matv;
    public String tentv;
    public String namsinh;
    public thanhvien(){

    }
    public thanhvien(int matv, String tentv, String namsinh) {
        this.matv = matv;
        this.tentv = tentv;
        this.namsinh = namsinh;

    }

    public int getMatv() {
        return matv;
    }

    public void setMatv(int matv) {
        this.matv = matv;
    }

    public String getTentv() {
        return tentv;
    }

    public void setTentv(String tentv) {
        this.tentv = tentv;
    }

    public String getNamsinh() {
        return namsinh;
    }

    public void setNamsinh(String namsinh) {
        this.namsinh = namsinh;
    }
}
