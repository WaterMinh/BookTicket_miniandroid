package com.example.appdatve;

import java.util.Date;

public class TIcketItemView {
    private int id;
    private Date date;
    private String ten_ga_di;
    private String ten_ga_den;

    public TIcketItemView(int id, Date date, String ten_ga_di, String ten_ga_den) {
        this.id = id;
        this.date = date;
        this.ten_ga_di = ten_ga_di;
        this.ten_ga_den = ten_ga_den;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTen_ga_di() {
        return ten_ga_di;
    }

    public void setTen_ga_di(String ten_ga_di) {
        this.ten_ga_di = ten_ga_di;
    }

    public String getTen_ga_den() {
        return ten_ga_den;
    }

    public void setTen_ga_den(String ten_ga_den) {
        this.ten_ga_den = ten_ga_den;
    }
}
