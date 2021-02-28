package com.example.design.Model;

public class Bill {
    private String total;

    public Bill(String total) {
        this.total = total;
    }
    public Bill(){

    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
