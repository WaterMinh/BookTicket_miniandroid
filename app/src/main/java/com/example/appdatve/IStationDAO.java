package com.example.appdatve;

import java.util.List;

public interface IStationDAO  {
    public List<Station>  selectAll();

    public Station selectByID(int id);
}
