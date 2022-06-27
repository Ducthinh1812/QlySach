package com.poly.assm.model;

public class sach {
    public int masach;
    public String tensach;
    public int giathue;
    public int matheloai;

    public sach(){

    }
    public sach(int masach, String tensach, int giathue, int matheloai) {
        this.masach = masach;
        this.tensach = tensach;
        this.giathue = giathue;
        this.matheloai = matheloai;
    }

    public int getMasach() {
        return masach;
    }

    public void setMasach(int masach) {
        this.masach = masach;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public int getGiathue() {
        return giathue;
    }

    public void setGiathue(int giathue) {
        this.giathue = giathue;
    }

    public int getMatheloai() {
        return matheloai;
    }

    public void setMatheloai(int matheloai) {
        this.matheloai = matheloai;
    }
}
