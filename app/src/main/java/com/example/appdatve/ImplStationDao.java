package com.example.appdatve;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ImplStationDao implements IStationDAO{
    private Context mCtx;

    public ImplStationDao(Context mCtx) {
        this.mCtx = mCtx;
    }

    @Override
    public List<Station> selectAll() {
        DatabaseHelper helper = new DatabaseHelper(mCtx);
        SQLiteDatabase db = helper.getWritableDatabase();

        String sql = "SELECT * FROM Station";
        Cursor c = db.rawQuery(sql, null);
        List<Station> lst = new ArrayList<>();
        while (c.moveToNext()) {
            int id = c.getInt(c.getColumnIndex("id"));
            String StationName = c.getString(c.getColumnIndex("StationName"));

            Station st = new Station(id, StationName);

            lst.add(st);
        }

        return lst;
    }

    @Override
    public Station selectByID(int id) {
        DatabaseHelper helper = new DatabaseHelper(mCtx);
        SQLiteDatabase db = helper.getWritableDatabase();

        String sql = "SELECT * FROM Station where id = ?";
        Cursor c = db.rawQuery(sql, new String[]{String.valueOf(id)});
        while (c.moveToNext()) {

            String StationName = c.getString(c.getColumnIndex("StationName"));

            Station st = new Station(id, StationName);
            return st;
        }

        return null;
    }


}
