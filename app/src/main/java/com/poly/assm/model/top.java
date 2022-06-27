package com.poly.assm.model;

public class top {
    public String tensach;
    public int soluong;
    public top(){

    }

    public top(String tensach, int soluong) {
        this.tensach = tensach;
        this.soluong = soluong;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
