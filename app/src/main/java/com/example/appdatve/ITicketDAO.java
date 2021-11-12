package com.example.appdatve;

import java.text.ParseException;
import java.util.List;

public interface ITicketDAO {
    public List<Ticket> selecAll() throws ParseException;
    public boolean insert(Ticket ticket);
    public boolean update(Ticket ticket);
    public boolean delete(int id);
    public Ticket selectById(int id) throws ParseException;

}
