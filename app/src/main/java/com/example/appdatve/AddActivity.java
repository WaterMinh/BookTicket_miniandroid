package com.example.appdatve;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        unitForm();
    }
//    private void loadStation(){
//        String station[] = {"Hà tĩnh","Sơn La", "Hải Phòng", "Hải Dương", "Vũng tàu"};
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,station);
//        Spinner gaden = findViewById(R.id.spnGaDen);
//    }
    private void unitForm() {
        // Lấy dữ liệu Station
        IStationDAO dao = new ImplStationDao(this);
        List<Station> lst = dao.selectAll();

        // Tạo Adapter
        ArrayAdapter<Station> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lst);

        // Lấy Spinner và đổ dữ liệu adapter
        ((Spinner) findViewById(R.id.spnGaDi)).setAdapter(adapter);
        ((Spinner) findViewById(R.id.spnGaDen)).setAdapter(adapter);
    }


    public void booking(View view) throws ParseException {
        // Lấy dữ liệu form
        String name = ((EditText) findViewById(R.id.editTen)).getText().toString();
        String tele_number = ((EditText) findViewById(R.id.editTele)).getText().toString();

        Spinner spStation = findViewById(R.id.spnGaDi);
        Station st = (Station) spStation.getSelectedItem();
        int fromStation = st.getId();

        Spinner spStation2 = findViewById(R.id.spnGaDen);
        Station st2 = (Station) spStation2.getSelectedItem();
        int toStation = st2.getId();

        boolean typeTicket = ((RadioGroup) findViewById(R.id.radLoaiVe)).createAccessibilityNodeInfo().isChecked();

        String strDeparture_date = ((EditText)findViewById(R.id.txtngayDi)).getText().toString();
        String strArrival_date = ((EditText)findViewById(R.id.txtngayDen)).getText().toString();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date departure_date = dateFormat.parse(strDeparture_date);
        Date arrival_date = dateFormat.parse(strArrival_date);


        int num_adults = Integer.parseInt(((EditText) findViewById(R.id.txtNguoiLon)).getText().toString());
        int num_chidren = Integer.parseInt(((EditText) findViewById(R.id.txtTreEm)).getText().toString());


        // Khởi tạo đối tượng Station
        Ticket ticket = new Ticket(name, tele_number, fromStation, toStation, departure_date, arrival_date, num_adults, num_chidren,typeTicket);

        // Đối tượng DAO
        ITicketDAO dao = new ImplTicketDao(this);
        boolean isOk = dao.insert(ticket);

        if (isOk) {
            Toast.makeText(this, "Đặt vé thành công", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Đặt vé không thành công", Toast.LENGTH_SHORT).show();
        }

    }

    public void openDatePicker(View view) {
        EditText ngayDi = findViewById(R.id.txtngayDi);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar calen = Calendar.getInstance();
                calen.set(year,month, dayOfMonth);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                ngayDi.setText(format.format(calen.getTime()));

            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, listener, year, month, day);
        datePickerDialog.show();
    }

    public void openDatePickerCome(View view) {
        EditText ngayDen = findViewById(R.id.txtngayDen);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar calen = Calendar.getInstance();
                calen.set(year,month, dayOfMonth);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                ngayDen.setText(format.format(calen.getTime()));

            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, listener, year, month, day);
        datePickerDialog.show();
    }
}