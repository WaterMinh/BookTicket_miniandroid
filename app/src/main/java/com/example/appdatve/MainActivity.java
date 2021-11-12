package com.example.appdatve;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<TIcketItemView> mLst;
    AdapterTicket mAdapter;
    ListView lstView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            loadTicket();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        registerForContextMenu(lstView);
    }


    private void loadTicket() throws ParseException {
        //Kết nối csdl lấy danh sách vé đã đặt
        ITicketDAO dao = new ImplTicketDao(this);
        IStationDAO sdao = new ImplStationDao(this);

         List<Ticket> lstTicket = dao.selecAll();

        mLst = new ArrayList<>();

         for(Ticket ticket : lstTicket){
            int id = ticket.getId();
            String ten_ga_di = sdao.selectByID(ticket.getFromStation()).getStationName();
             String ten_ga_den = sdao.selectByID(ticket.getToStation()).getStationName();
             Date date = ticket.getDeparture_date();

             TIcketItemView item = new TIcketItemView(id,date, ten_ga_di , ten_ga_den);
             mLst.add(item);
         }

        // Tạo Adapter
         mAdapter = new AdapterTicket(this, R.layout.item_ticket, mLst);

        //Đổ dữ liệu lên ListView
        lstView = findViewById(R.id.listTicket);
        lstView.setAdapter(mAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.menuAdd:
                Intent AddNew = new Intent(this, AddActivity.class);
                startActivity(AddNew);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            loadTicket();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.sub_menu,menu);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle("Bạn đã lựa chọn:" + mLst.get(info.position).getId());
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        TIcketItemView tk = mLst.get(info.position);
        switch (id){
            case R.id.menuUpdate:
                Intent Update = new Intent(this, UpdateActivity.class);
                int idTicket = tk.getId();
                Update.putExtra("idtk", idTicket);
                startActivity(Update);
                break;
            case R.id.menuDelete:
                ITicketDAO dao = new ImplTicketDao(this);
                boolean isOk = dao.delete(tk.getId());
                if (isOk) {
                    Toast.makeText(this, "Xóa thông tin" + tk.getId() + "thành công", Toast.LENGTH_SHORT).show();
                    mLst.remove(info.position);
                    mAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                }

        }
        return super.onContextItemSelected(item);
    }
}