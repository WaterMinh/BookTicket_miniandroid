package com.example.appdatve;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ImplTicketDao  implements ITicketDAO{
    private Context mCtx;
    public ImplTicketDao (Context mCtx){
        this.mCtx = mCtx;
    }


    @Override
    public List<Ticket> selecAll() throws ParseException {

        // mở kết nối database
        DatabaseHelper helper = new DatabaseHelper(mCtx);
        SQLiteDatabase db = helper.getWritableDatabase();

        String sql = "SELECT * FROM Ticket";
        Cursor c  = db.rawQuery(sql, null);

        List<Ticket> lst = new ArrayList<>();
        while (c.moveToNext()){
            int id = c.getInt(c.getColumnIndex("id"));
            String name = c.getString(c.getColumnIndex("name"));
            String tele_number = c.getString(c.getColumnIndex("tele_number"));
            int fromStation = c.getInt(c.getColumnIndex("fromStation"));
            int toStation = c.getInt(c.getColumnIndex("toStation"));

//            String str_departure_date = c.getString(c.getColumnIndex("departure_date"));
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date departure_date = dateFormat.parse(c.getString(c.getColumnIndex("departure_date")));



//            String str_arrival_date = c.getString(c.getColumnIndex("arrival_date"));
            Date arrival_date = dateFormat.parse(c.getString(c.getColumnIndex("arrival_date")));

            int num_adults = c.getInt(c.getColumnIndex("num_adults"));
            int num_children = c.getInt(c.getColumnIndex("num_children"));
            boolean typeTicket = c.getInt(c.getColumnIndex("typeTicket")) > 0;

            Ticket tk = new Ticket(id, name, tele_number, fromStation, toStation, departure_date,arrival_date, num_adults, num_children, typeTicket);
            lst.add(tk);
        }
        return lst;
    }


    @Override
    public boolean insert(Ticket ticket) {
        DatabaseHelper helper = new DatabaseHelper(mCtx);
        SQLiteDatabase db = helper.getWritableDatabase();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Đối tượng chứa dữ liệu >>> Insert
        ContentValues item = new ContentValues();
        item.put("name", ticket.getName());
        item.put("tele_number", ticket.getTele_number());
        item.put("fromStation", ticket.getFromStation());
        item.put("toStation", ticket.getToStation());
        item.put("typeTicket", ticket.isTypeTicket());

        item.put("departure_date",dateFormat.format(ticket.getDeparture_date()));
        item.put("arrival_date",dateFormat.format(ticket.getArrival_date()));
//        item.put("departure_date", dateFormat.getDateFormatSymbols(ticket.getDeparture_date() , ));
        item.put("num_adults", ticket.getNum_adults());
        item.put("num_children", ticket.getNum_children());

        // Lệnh thêm dữ liệu
        long newID = db.insert("Ticket", null, item);
        if (newID > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Ticket ticket) {
        DatabaseHelper helper = new DatabaseHelper(mCtx);
        SQLiteDatabase db = helper.getWritableDatabase();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Đối tượng chứa dữ liệu >>> Update
        ContentValues item = new ContentValues();
        item.put("name", ticket.getName());
        item.put("tele_number", ticket.getTele_number());
        item.put("fromStation", ticket.getFromStation());
        item.put("toStation", ticket.getToStation());
        item.put("typeTicket", ticket.isTypeTicket());

        item.put("departure_date",dateFormat.format(ticket.getDeparture_date()));
        item.put("arrival_date",dateFormat.format(ticket.getArrival_date()));

        item.put("num_adults", ticket.getNum_adults());
        item.put("num_children", ticket.getNum_children());

        int row = db.update("Ticket", item, "id = ?", new String[]{String.valueOf(ticket.getId())});

        if (row > 0){
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        DatabaseHelper helper = new DatabaseHelper(mCtx);
        SQLiteDatabase db = helper.getWritableDatabase();

        int row = db.delete("Ticket", "id=?", new String [] {String.valueOf(id)});
        if (row > 0){
            return true;
        }

        return false;
    }

    @Override
    public Ticket selectById(int idtk) throws ParseException {
        DatabaseHelper helper = new DatabaseHelper(mCtx);
        SQLiteDatabase db = helper.getWritableDatabase();

        String sql = "SELECT * FROM Ticket WHERE id= ?";

        Cursor c = db.rawQuery(sql, new String[]{String.valueOf(idtk)});
        while (c.moveToNext()){

            SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
            int id = c.getInt(c.getColumnIndex("id"));
            String name = c.getString(c.getColumnIndex("name"));
            String tele_number = c.getString(c.getColumnIndex("tele_number"));
            int fromStation = c.getInt(c.getColumnIndex("fromStation"));
            int toStation = c.getInt(c.getColumnIndex("toStation"));
            boolean typeTicket = c.getInt(c.getColumnIndex("typeTicket")) > 0;
            String strdeparture_date = c.getString(c.getColumnIndex("departure_date"));
            String strarrival_date = c.getString(c.getColumnIndex("arrival_date"));

            Date departure_date = formate.parse(strdeparture_date);
            Date arrival_date = formate.parse(strarrival_date);

            int num_adults = c.getInt(c.getColumnIndex("num_adults"));
            int num_children = c.getInt(c.getColumnIndex("num_children"));

            Ticket tk = new Ticket(id, name, tele_number, fromStation, toStation, departure_date, arrival_date, num_adults, num_children , typeTicket);
            return tk;


        }
        return null;
    }
}
