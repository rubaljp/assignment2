package com.example.carrental.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carrental.GetterSetter.Rental;
import com.example.carrental.GetterSetter.Reservation;
import com.example.carrental.GetterSetter.Transaction;
import com.example.carrental.GetterSetter.Vehicle;
import com.example.carrental.Others.Utils;
import com.example.carrental.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;
import java.util.UUID;

public class ViewVehicleDetails extends AppCompatActivity implements View.OnClickListener, TextWatcher
{
    Button rentButton, reservedButton;
    TextView startDate, dueDate;
    ImageView vehicleImage;
    TextView tvCarType, tvCarBrand, tvCarModel, tvCarYear, tvCarColor, tvCarLicense;

    ArrayList<Vehicle> vehicles = VehicleDetails.vehicles;
    public static ArrayList<Transaction> transactions = new ArrayList<>();
    private static ArrayList<Reservation> reservedVehicles = new ArrayList<>();
    private static ArrayList<Rental> rentedVehicles = new ArrayList<>();

    Vehicle vehicle;
    int position;

    Calendar calendar;
    int year, month, dayOfMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_vehicle_details);

        vehicleImage = (ImageView) findViewById(R.id.vehicleImage);
        tvCarType = (TextView) findViewById(R.id.tvCarType);
        tvCarBrand = (TextView) findViewById(R.id.tvCarBrand);
        tvCarModel = (TextView) findViewById(R.id.tvCarModel);
        tvCarYear = (TextView) findViewById(R.id.tvCarYear);
        tvCarColor = (TextView) findViewById(R.id.tvCarColor);
        tvCarLicense = (TextView) findViewById(R.id.tvCarLicense);

        startDate = (TextView) findViewById(R.id.startDate);
        dueDate = (TextView) findViewById(R.id.dueDate);

        rentButton = (Button) findViewById(R.id.rentButton);
        reservedButton = (Button) findViewById(R.id.reservedButton);
        rentButton.setEnabled(false);
        reservedButton.setEnabled(false);

        if(vehicles.size() > 0)
        {
            position = Objects.requireNonNull(getIntent().getExtras()).getInt("position");
            vehicle = vehicles.get(position);

            try
            {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), vehicle.getImageUri());
                vehicleImage.setImageBitmap(bitmap);
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            tvCarType.setText(vehicle.getType());
            tvCarBrand.setText(vehicle.getBrand());
            tvCarModel.setText(vehicle.getModel());
            tvCarYear.setText(vehicle.getYear());
            tvCarColor.setText(vehicle.getColor());
            tvCarLicense.setText(vehicle.getLicensePlate());
        }

        startDate.setOnClickListener(this);
        dueDate.setOnClickListener(this);
        rentButton.setOnClickListener(this);
        startDate.addTextChangedListener(this);
        dueDate.addTextChangedListener(this);
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, CustomerOperation.class);
        startActivity(intent);
        finish();
    }

    private void initializeCalendar()
    {
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
    }

    private void RentVehicle()
    {
        Transaction transaction = new Transaction();

        Rental rental = new Rental();
        rental.setStartDate(startDate.getText().toString());
        rental.setDueDate(dueDate.getText().toString());
        rental.setLicensePlate(vehicle.getLicensePlate());
        rental.setRegistrationNumber(Utils.getRandomNumber());

        rentedVehicles.add(rental);

        transaction.setRentals(rentedVehicles);
        transactions.add(transaction);

        Toast.makeText(this, "Car Rented Successfully.", Toast.LENGTH_SHORT).show();
        rentButton.setEnabled(false);
        reservedButton.setEnabled(false);
    }

    private void ReservedVehicle()
    {
        Transaction transaction = new Transaction();

        Reservation reservation = new Reservation();
        reservation.setStartDate(startDate.getText().toString());
        reservation.setEndDate(dueDate.getText().toString());
        reservation.setLicensePlate(vehicle.getLicensePlate());
        reservation.setRegistrationNumber(Utils.getRandomNumber());

        reservedVehicles.add(reservation);

        transaction.setReservations(reservedVehicles);
        transactions.add(transaction);

        Toast.makeText(this, "Car Reserved Successfully.", Toast.LENGTH_SHORT).show();
        rentButton.setEnabled(false);
        reservedButton.setEnabled(false);
    }

    @Override
    public void onClick(View view)
    {
        if(view.getId() == R.id.startDate)
        {
            initializeCalendar();
            DatePickerDialog datePickerDialog = new DatePickerDialog(ViewVehicleDetails.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                    String date = dayOfMonth + "/" + month + "/" + year;
                    startDate.setText(date);

                }
            }, year, month, dayOfMonth);
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            datePickerDialog.show();
        }
        else if(view.getId() == R.id.dueDate)
        {
            initializeCalendar();
            DatePickerDialog datePickerDialog = new DatePickerDialog(ViewVehicleDetails.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                    String date = dayOfMonth + "/" + month + "/" + year;
                    dueDate.setText(date);

                }
            }, year, month, dayOfMonth);
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            datePickerDialog.show();
        }
        else if(view.getId() == R.id.rentButton)
        {
            if(Utils.checkVehicleIsRented(vehicle))
                Toast.makeText(this, "This Vehicle is Aready Rented !", Toast.LENGTH_SHORT).show();
            else
                RentVehicle();
        }
        else if(view.getId() == R.id.reservedButton)
        {
            if(Utils.checkVehicleIsReserved(vehicle))
                Toast.makeText(this, "This Vehicle is Aready Reserved !", Toast.LENGTH_SHORT).show();
            else
                ReservedVehicle();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count)
    {
        String strStartDate = startDate.getText().toString().trim();
        String strEndDate = dueDate.getText().toString().trim();

        if(!strStartDate.equals("Select Start Date") && strEndDate.equals("Select Due Date"))
        {
            rentButton.setEnabled(false);
            reservedButton.setEnabled(false);
        }
        else if(strStartDate.equals("Select Start Date") && !strEndDate.equals("Select Due Date"))
        {
            rentButton.setEnabled(false);
            reservedButton.setEnabled(false);
        }
        else if(!strStartDate.equals("Select Start Date") && !strEndDate.equals("Select Due Date"))
        {
            rentButton.setEnabled(true);
            reservedButton.setEnabled(true);
        }
    }
    @Override
    public void afterTextChanged(Editable s) {

    }
}
