package com.example.carrental.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carrental.Adapters.VehicleAdapter;
import com.example.carrental.Adapters.VehicleAdapterTwo;
import com.example.carrental.GetterSetter.User;
import com.example.carrental.GetterSetter.Vehicle;
import com.example.carrental.Others.Utils;
import com.example.carrental.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;

public class CustomerOperation extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener
{
    TextView tvNoVehicles, startDate, endDate;
    Button btnLogout, searchVehicle;
    ListView vehicleListView;

    private boolean doubleBackToExitPressedOnce = false;
    ArrayList<Vehicle> vehicleArrayList = VehicleDetails.vehicles;

    Calendar calendar;
    int year, month, dayOfMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_operation);

        vehicleListView = (ListView) findViewById(R.id.vehicleListView);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        searchVehicle = (Button) findViewById(R.id.searchVehicle);

        tvNoVehicles = (TextView) findViewById(R.id.tvNoVehicles);
        startDate = (TextView) findViewById(R.id.startDate);
        endDate = (TextView) findViewById(R.id.endDate);

        if(vehicleArrayList != null)
        {
            vehicleListView.setVisibility(View.VISIBLE);
            tvNoVehicles.setVisibility(View.GONE);

            VehicleAdapterTwo adapter = new VehicleAdapterTwo(this, vehicleArrayList);
            vehicleListView.setAdapter(adapter);
        }
        else
        {
            vehicleListView.setVisibility(View.GONE);
            tvNoVehicles.setVisibility(View.VISIBLE);
        }

        btnLogout.setOnClickListener(this);
        startDate.setOnClickListener(this);
        endDate.setOnClickListener(this);
        vehicleListView.setOnItemClickListener(this);
        searchVehicle.setOnClickListener(this);
    }

    @Override
    public void onBackPressed()
    {
        if(doubleBackToExitPressedOnce)
        {
            super.onBackPressed();
        }
        else
        {
//          Getting exit after user press double back
            doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
    }

    @Override
    public void onClick(View view)
    {
        if(view.getId() == R.id.btnLogout)
        {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
            finish();
        }
        else if(view.getId() == R.id.startDate)
        {
            initializeCalendar();
            DatePickerDialog datePickerDialog = new DatePickerDialog(CustomerOperation.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                    String date = dayOfMonth + "/" + month + "/" + year;
                    startDate.setText(date);

                }
            }, year, month, dayOfMonth);
            datePickerDialog.show();
        }
        else if(view.getId() == R.id.endDate)
        {
            initializeCalendar();
            DatePickerDialog datePickerDialog = new DatePickerDialog(CustomerOperation.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                    String date = dayOfMonth + "/" + month + "/" + year;
                    endDate.setText(date);

                }
            }, year, month, dayOfMonth);
            datePickerDialog.show();
        }
        else if(view.getId() == R.id.searchVehicle)
        {

        }
    }

    private void initializeCalendar()
    {
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        int itemIndex = Utils.getVehicleIndex(vehicleArrayList.get(position).getUniqueID());
        Intent intent = new Intent(CustomerOperation.this, ViewVehicleDetails.class);
        intent.putExtra("position", itemIndex);
        startActivity(intent);
        finish();
    }
}