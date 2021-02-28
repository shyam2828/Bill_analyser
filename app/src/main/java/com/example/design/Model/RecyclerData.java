package com.example.design.Model;

import androidx.recyclerview.widget.RecyclerView;

public class RecyclerData {
    String title;
    String description;
    RecyclerView data;
    String tax;
    String rate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RecyclerView getData() {
        return data;
    }

    public void setData(RecyclerView data) {
        this.data = data;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}

