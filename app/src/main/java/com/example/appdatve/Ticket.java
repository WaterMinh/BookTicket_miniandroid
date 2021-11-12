package com.example.appdatve;

import java.util.Date;

public class Ticket {
    private int id;
    private String name;
    private String tele_number;
    private int fromStation;
    private int toStation;
    private Date departure_date;
    private Date arrival_date;
    private int num_adults;
    private int num_children;
    private boolean typeTicket;

    public Ticket(int id, String name, String tele_number, int fromStation, int toStation, Date departure_date, Date arrival_date, int num_adults, int num_children, boolean typeTicket) {
        this.id = id;
        this.name = name;
        this.tele_number = tele_number;
        this.fromStation = fromStation;
        this.toStation = toStation;
        this.departure_date = departure_date;
        this.arrival_date = arrival_date;
        this.num_adults = num_adults;
        this.num_children = num_children;
        this.typeTicket = typeTicket;
    }

    public Ticket(String name, String tele_number, int fromStation, int toStation, Date departure_date, Date arrival_date, int num_adults, int num_children, boolean typeTicket) {
        this.name = name;
        this.tele_number = tele_number;
        this.fromStation = fromStation;
        this.toStation = toStation;
        this.departure_date = departure_date;
        this.arrival_date = arrival_date;
        this.num_adults = num_adults;
        this.num_children = num_children;
        this.typeTicket = typeTicket;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTele_number() {
        return tele_number;
    }

    public void setTele_number(String tele_number) {
        this.tele_number = tele_number;
    }

    public int getFromStation() {
        return fromStation;
    }

    public void setFromStation(int fromStation) {
        this.fromStation = fromStation;
    }

    public int getToStation() {
        return toStation;
    }

    public void setToStation(int toStation) {
        this.toStation = toStation;
    }

    public Date getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(Date departure_date) {
        this.departure_date = departure_date;
    }

    public Date getArrival_date() {
        return arrival_date;
    }

    public void setArrival_date(Date arrival_date) {
        this.arrival_date = arrival_date;
    }

    public int getNum_adults() {
        return num_adults;
    }

    public void setNum_adults(int num_adults) {
        this.num_adults = num_adults;
    }

    public int getNum_children() {
        return num_children;
    }

    public void setNum_children(int num_children) {
        this.num_children = num_children;
    }

    public boolean isTypeTicket() {
        return typeTicket;
    }

    public void setTypeTicket(boolean typeTicket) {
        this.typeTicket = typeTicket;
    }
}
