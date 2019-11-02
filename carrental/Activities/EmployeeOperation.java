package com.example.carrental.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carrental.Adapters.VehicleAdapter;
import com.example.carrental.GetterSetter.User;
import com.example.carrental.GetterSetter.Vehicle;
import com.example.carrental.Others.CustomDialog;
import com.example.carrental.Others.Utils;
import com.example.carrental.R;

import java.util.ArrayList;

public class EmployeeOperation extends AppCompatActivity implements AdapterView.OnItemLongClickListener, View.OnClickListener
{
    private static EmployeeOperation instance;

    ListView customerListView, vehicleListView;
    TextView tvNoCustomers, tvNoVehicles;
    Button btnCreateCustomer, btnViewCustomers, btnViewVehicles, btnLogout, btnSpecialLogout, btnCreateVehicle, btnLogout1;
    RelativeLayout CustomerDataLayout, ChoicesLayout, VehicleDataLayout;

    ArrayList<User> userArrayList = UserDetails.users;
    ArrayList<Vehicle> vehicleArrayList = VehicleDetails.vehicles;
    private boolean doubleBackToExitPressedOnce = false;

    public ArrayAdapter customerAdapter = null;
    public VehicleAdapter vehicleAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_operation);

        instance = this;

        customerListView = (ListView) findViewById(R.id.customerListView);
        vehicleListView = (ListView) findViewById(R.id.vehicleListView);

        btnCreateCustomer = (Button) findViewById(R.id.btnCreateCustomer);
        btnViewCustomers = (Button) findViewById(R.id.btnViewCustomers);
        btnViewVehicles = (Button) findViewById(R.id.btnViewVehicles);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnSpecialLogout = (Button) findViewById(R.id.btnSpecialLogout);
        btnCreateVehicle = (Button) findViewById(R.id.btnCreateVehicle);
        btnLogout1 = (Button) findViewById(R.id.btnLogout1);

        CustomerDataLayout = (RelativeLayout) findViewById(R.id.CustomerDataLayout);
        ChoicesLayout = (RelativeLayout) findViewById(R.id.ChoicesLayout);
        VehicleDataLayout = (RelativeLayout) findViewById(R.id.VehicleDataLayout);

        tvNoCustomers = (TextView) findViewById(R.id.tvNoCustomers);
        tvNoVehicles = (TextView) findViewById(R.id.tvNoVehicles);

        if(userArrayList != null)
        {
            ArrayList<String> customerName = new ArrayList<>();
            for(User user : userArrayList)
            {
                String fullName = user.getFirstname()+" "+user.getLastname();
                customerName.add(fullName);
            }

            customerListView.setVisibility(View.VISIBLE);
            tvNoCustomers.setVisibility(View.GONE);

            customerAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, customerName);
            customerListView.setAdapter(customerAdapter);
        }
        else
        {
            customerListView.setVisibility(View.GONE);
            tvNoCustomers.setVisibility(View.VISIBLE);
        }

        if(vehicleArrayList != null)
        {
            customerListView.setVisibility(View.VISIBLE);
            tvNoCustomers.setVisibility(View.GONE);

            vehicleAdapter = new VehicleAdapter(this, vehicleArrayList);
            vehicleListView.setAdapter(vehicleAdapter);
        }
        else
        {
            vehicleListView.setVisibility(View.GONE);
            tvNoVehicles.setVisibility(View.VISIBLE);
        }

        btnCreateCustomer.setOnClickListener(this);
        btnViewCustomers.setOnClickListener(this);
        btnCreateVehicle.setOnClickListener(this);
        btnViewVehicles.setOnClickListener(this);
        customerListView.setOnItemLongClickListener(this);
        vehicleListView.setOnItemLongClickListener(this);
        CustomerDataLayout.setOnClickListener(this);
        ChoicesLayout.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
        btnSpecialLogout.setOnClickListener(this);
        btnLogout1.setOnClickListener(this);
    }

    @Override
    public void onBackPressed()
    {
        if(CustomerDataLayout.getVisibility() == View.VISIBLE)
        {
            ChoicesLayout.setVisibility(View.VISIBLE);
            CustomerDataLayout.setVisibility(View.GONE);
        }
        else if(VehicleDataLayout.getVisibility() == View.VISIBLE)
        {
            ChoicesLayout.setVisibility(View.VISIBLE);
            VehicleDataLayout.setVisibility(View.GONE);
        }
        else if(ChoicesLayout.getVisibility() == View.VISIBLE)
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
        else
        {
            super.onBackPressed();
        }

    }

    @Override
    public void onClick(View view)
    {
        int resourceId = view.getId();

        if(resourceId == R.id.btnCreateCustomer)
        {
            Intent intent = new Intent(this, UserDetails.class);
            startActivity(intent);
            finish();
        }
        if(resourceId == R.id.btnCreateVehicle)
        {
            Intent intent = new Intent(this, VehicleDetails.class);
            startActivity(intent);
            finish();
        }
        else if(resourceId == R.id.btnViewCustomers)
        {
            Utils.IsUserView = "Customers";
;           ChoicesLayout.setVisibility(View.GONE);
            CustomerDataLayout.setVisibility(View.VISIBLE);
        }
        else if(resourceId == R.id.btnViewVehicles)
        {
            Utils.IsUserView = "Vehicles";
            ChoicesLayout.setVisibility(View.GONE);
            VehicleDataLayout.setVisibility(View.VISIBLE);
        }
        else if(resourceId == R.id.btnLogout)
        {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
            finish();
        }
        else if(resourceId == R.id.btnLogout1)
        {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
            finish();
        }
        else if(resourceId == R.id.btnSpecialLogout)
        {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
    {
        int itemIndex = 0;
        if(Utils.IsUserView.equals("Customers"))
        {
            itemIndex = Utils.getUserIndex(userArrayList.get(position).getUniqueId());
            CustomDialog.customerDialog(this, itemIndex);
        }
        else if(Utils.IsUserView.equals("Vehicles"))
        {
            itemIndex = Utils.getVehicleIndex(vehicleArrayList.get(position).getUniqueID());
            CustomDialog.customerDialog(this, itemIndex);
        }

        return true;
    }

    public static EmployeeOperation getInstance() { return instance; }
}