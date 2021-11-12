package com.example.appdatve;

import androidx.annotation.NonNull;

import java.util.Date;

public class Station {
    private int id;
    private String StationName;

    public Station(int id, String stationName) {
        this.id = id;
        StationName = stationName;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStationName() {
        return StationName;
    }

    public void setStationName(String stationName) {
        StationName = stationName;
    }

    @NonNull
    @Override
    public String toString() {
        return this.StationName;
    }
}
