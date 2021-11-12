package com.example.appdatve;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UpdateActivity extends AppCompatActivity {
    Ticket TkUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        try {
            unitForm();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void unitForm() throws ParseException {
        // Lấy dữ liệu Station
        IStationDAO dao = new ImplStationDao(this);
        List<Station> lst = dao.selectAll();

        // Tạo Adapter
        ArrayAdapter<Station> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lst);

        // Lấy Spinner và đổ dữ liệu adapter
        ((Spinner) findViewById(R.id.spnGaDi)).setAdapter(adapter);
        ((Spinner) findViewById(R.id.spnGaDen)).setAdapter(adapter);

        //Lấy thông tin vé đổ lên form --> update
        int id = getIntent().getExtras().getInt("idtk");
        ITicketDAO daoTK = new ImplTicketDao(this);
        TkUpdate = daoTK.selectById(id);

        // Đổ sữ liệu lên form cập nhật
        ((EditText) findViewById(R.id.editTen)).setText(TkUpdate.getName());
        ((EditText) findViewById(R.id.editTele)).setText(TkUpdate.getTele_number());


        // Trỏ tới Spinner Station
        int position_GaDi = 0;
        for (int i = 0; i < lst.size(); i++) {
            Station station = lst.get(i);
            if (station.getId() == TkUpdate.getId()) {
                position_GaDi = i;
                break;
            }
        }
        ((Spinner) findViewById(R.id.spnGaDi)).setSelection(position_GaDi);

        int position_GaDen = 0;
        for (int i = 0; i < lst.size(); i++) {
            Station station = lst.get(i);
            if (station.getId() == TkUpdate.getId()) {
                position_GaDen = i;
                break;
            }
        }
        ((Spinner) findViewById(R.id.spnGaDen)).setSelection(position_GaDen);

        if (TkUpdate.isTypeTicket()) {
            ((RadioButton) findViewById(R.id.rdb1chieu)).setChecked(true);
            ((RadioButton) findViewById(R.id.rdbKhuHoi)).setChecked(false);
        } else {
            ((RadioButton) findViewById(R.id.rdb1chieu)).setChecked(false);
            ((RadioButton) findViewById(R.id.rdbKhuHoi)).setChecked(true);
        }

        ((EditText) findViewById(R.id.txtngayDi)).setText(TkUpdate.getDeparture_date() + "");
        ((EditText) findViewById(R.id.txtngayDen)).setText(TkUpdate.getArrival_date() + "");
        ((EditText) findViewById(R.id.txtNguoiLon)).setText(TkUpdate.getNum_adults() + "");
        ((EditText) findViewById(R.id.txtTreEm)).setText(TkUpdate.getNum_children() + "");


    }

    public void updateTicket(View view) throws ParseException {
        // Lấy dữ liệu form
        String name = ((EditText) findViewById(R.id.editTen)).getText().toString();
        String tele_number = ((EditText) findViewById(R.id.editTele)).getText().toString();

        Spinner spStation = findViewById(R.id.spnGaDi);
        Station st = (Station) spStation.getSelectedItem();
        int fromStation = st.getId();

        int toStation = st.getId();

        boolean typeTicket = ((RadioGroup) findViewById(R.id.radLoaiVe)).createAccessibilityNodeInfo().isChecked();

        String strDeparture_date = ((EditText)findViewById(R.id.txtngayDi)).getText().toString();
        String strArrival_date = ((EditText)findViewById(R.id.txtngayDen)).getText().toString();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date departure_date = dateFormat.parse(strDeparture_date);
        Date arrival_date = dateFormat.parse(strArrival_date);


        int num_adults = Integer.parseInt(((EditText) findViewById(R.id.txtNguoiLon)).getText().toString());
        int num_chidren = Integer.parseInt(((EditText) findViewById(R.id.txtTreEm)).getText().toString());


        // Khởi tạo đối tượng Station
        Ticket ticket = new Ticket(TkUpdate.getId(), name, tele_number, fromStation, toStation, departure_date, arrival_date, num_adults, num_chidren, typeTicket);

        // Đối tượng DAO
        ITicketDAO dao = new ImplTicketDao(this);
        boolean isOk = dao.update(ticket);

        if (isOk) {
            Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Cập nhật không thành công", Toast.LENGTH_SHORT).show();
        }
    }
}