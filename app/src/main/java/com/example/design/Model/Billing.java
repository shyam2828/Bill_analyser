package com.example.design.Model;

public class Billing {
    private String sender;
    private static String receiver;
    private String total;

    public Billing(String sender, String receiver, String total) {
        this.sender = sender;
        this.receiver = receiver;
        this.total = total;
    }

    public Billing()
    {

    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public static String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
