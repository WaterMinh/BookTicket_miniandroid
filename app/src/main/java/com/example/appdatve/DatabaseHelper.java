package com.example.appdatve;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "DatVe.sqlite";
    private static final int DATABASE_VERSION = 1;

    private Context mCtx;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mCtx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE Station (\n" +
                "    id          INTEGER       PRIMARY KEY AUTOINCREMENT,\n" +
                "    StationName VARCHAR (100) NOT NULL\n" +
                ");\n";
        db.execSQL(sql);

        sql="INSERT INTO Station(StationName) VALUES ('Hà Nội ')";
        db.execSQL(sql);
        sql="INSERT INTO Station(StationName) VALUES ('Hải Phòng')";
        db.execSQL(sql);
        sql="INSERT INTO Station(StationName) VALUES ('Hải Dương')";
        db.execSQL(sql);
        sql="INSERT INTO Station(StationName) VALUES ('Đà Nẵng')";
        db.execSQL(sql);
        sql="INSERT INTO Station(StationName) VALUES ('Vũng Tàu')";
        db.execSQL(sql);
        sql="INSERT INTO Station(StationName) VALUES ('Tp.HCM')";
        db.execSQL(sql);

         sql = "CREATE TABLE Ticket (\n" +
                "    id             INTEGER    PRIMARY KEY AUTOINCREMENT,\n" +
                "    name           VARCHAR (100) NOT NULL,\n" +
                "    tele_number    VARCHAR(10)  NOT NULL,\n" +
                "    fromStation    INT     REFERENCES Station (id),\n" +
                "    toStation      INT     REFERENCES Station (id),\n" +
                "    departure_date DATE,\n" +
                "    arrival_date   DATE,\n" +
                "    num_adults     INT        NOT NULL,\n" +
                "    num_children   INT        NOT NULL,\n" +
                "    typeTicket     BOOLEAN    DEFAULT (1) \n" +
                ");\n";
        db.execSQL(sql);


        sql = "INSERT INTO Ticket(name, tele_number, fromStation, toStation, departure_date, arrival_date, num_adults, num_children) VALUES ('Ben', '0365489266', 1, 4, '2021-01-12',' 2022-01-13', 3, 4)";
        db.execSQL(sql);
        sql = "INSERT INTO Ticket(name, tele_number, fromStation, toStation, departure_date, arrival_date, num_adults, num_children) VALUES ('Binh Binh', '0365489266', 2, 3, '2021-01-12',' 2022-01-13', 3, 4)";
        db.execSQL(sql);
        sql = "INSERT INTO Ticket(name, tele_number, fromStation, toStation, departure_date, arrival_date, num_adults, num_children) VALUES ('Sóc', '0365489266', 3, 2, '2021-01-12',' 2022-01-13', 3, 4)";
        db.execSQL(sql);
        sql = "INSERT INTO Ticket(name, tele_number, fromStation, toStation, departure_date, arrival_date, num_adults, num_children) VALUES ('Gấu', '0365489266', 4, 1, '2021-01-12',' 2022-01-13', 3, 4)";
        db.execSQL(sql);




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
