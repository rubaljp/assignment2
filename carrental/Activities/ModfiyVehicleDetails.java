package com.example.carrental.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.carrental.GetterSetter.User;
import com.example.carrental.GetterSetter.Vehicle;
import com.example.carrental.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class ModfiyVehicleDetails extends AppCompatActivity implements View.OnClickListener
{
    ImageView vehicleImage;
    EditText edtType, edtBrand, edtModel, edtYear, edtColor, edtLicensePlate;
    Button btnSaveDetails, btnUpdateDetails;

    private int PICK_IMAGE_REQUEST = 1;
    private Uri IMAGE_URI = null;

    private ArrayList<Vehicle> vehicles = VehicleDetails.vehicles;

    Vehicle vehicle = null;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vehicle_details);

        edtType = (EditText) findViewById(R.id.edtType);
        edtBrand = (EditText) findViewById(R.id.edtBrand);
        edtModel = (EditText) findViewById(R.id.edtModel);
        edtYear = (EditText) findViewById(R.id.edtYear);
        edtColor = (EditText) findViewById(R.id.edtColor);
        edtLicensePlate = (EditText) findViewById(R.id.edtLicensePlate);

        vehicleImage = (ImageView) findViewById(R.id.vehicleImage);

        btnUpdateDetails = (Button) findViewById(R.id.btnUpdateDetails);
        btnUpdateDetails.setVisibility(View.VISIBLE);

        position = Objects.requireNonNull(getIntent().getExtras()).getInt("position");
        vehicle = vehicles.get(position);

        edtType.setText(vehicle.getType());
        edtBrand.setText(vehicle.getBrand());
        edtModel.setText(vehicle.getModel());
        edtYear.setText(vehicle.getYear());
        edtColor.setText(vehicle.getColor());
        edtLicensePlate.setText(vehicle.getLicensePlate());

        try
        {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), vehicle.getImageUri());
            vehicleImage.setImageBitmap(bitmap);
            vehicleImage.setTag("gallery");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        btnUpdateDetails.setOnClickListener(this);
        vehicleImage.setOnClickListener(this);
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, EmployeeOperation.class);
        startActivity(intent);
        finish();
    }

    private boolean alreadyExist()
    {
        for(Vehicle existingVehicle : vehicles)
        {
            if(edtLicensePlate.getText().toString().trim().equals(existingVehicle.getLicensePlate()))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onClick(View view)
    {
        int resourceId = view.getId();

        if(resourceId == R.id.btnUpdateDetails)
        {
            if("gallery".equals(vehicleImage.getTag()) && !edtType.getText().toString().equals("") && !edtBrand.getText().toString().equals("") &&
                    !edtModel.getText().toString().equals("") && !edtYear.getText().toString().equals("") &&
                    !edtColor.getText().toString().equals("") && !edtLicensePlate.getText().toString().equals(""))
            {
                Vehicle updatingVehicle = new Vehicle();

                updatingVehicle.setType(edtType.getText().toString().trim());
                updatingVehicle.setBrand(edtBrand.getText().toString().trim());
                updatingVehicle.setModel(edtModel.getText().toString().trim());
                updatingVehicle.setYear(edtYear.getText().toString().trim());
                updatingVehicle.setColor(edtColor.getText().toString().trim());
                updatingVehicle.setLicensePlate(edtLicensePlate.getText().toString().trim());

                if(IMAGE_URI == null)
                    updatingVehicle.setImageUri(vehicle.getImageUri());
                else
                    updatingVehicle.setImageUri(IMAGE_URI);

                if(vehicles.size() > 0)
                {
                    for(Vehicle existingVehicle : vehicles)
                    {
                        if(!edtLicensePlate.getText().toString().trim().equals(vehicle.getLicensePlate()))
                        {
                            if(alreadyExist())
                                Toast.makeText(this, "License Plate No. Already Exist", Toast.LENGTH_SHORT).show();
                        }
                        else if(!edtLicensePlate.getText().toString().trim().equals(existingVehicle.getLicensePlate()) ||
                                        edtLicensePlate.getText().toString().trim().equals(vehicle.getLicensePlate()))
                        {
                            vehicles.set(position, updatingVehicle);
                            Toast.makeText(this, "Vehicle Updated Successfully", Toast.LENGTH_SHORT).show();

                            edtType.setText("");
                            edtBrand.setText("");
                            edtModel.setText("");
                            edtYear.setText("");
                            edtColor.setText("");
                            edtLicensePlate.setText("");
                            vehicleImage.setImageResource(R.drawable.ic_gallery);
                        }
                    }
                }
            }
            else
            {
                Toast.makeText(this, "Entire details needed to update details !", Toast.LENGTH_SHORT).show();
            }
        }
        else if(resourceId == R.id.vehicleImage)
        {
            //Create an Intent with action as ACTION_PICK
            Intent intent=new Intent(Intent.ACTION_PICK);
            // Sets the type as image/*. This ensures only components of type image are selected
            intent.setType("image/*");
            //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
            String[] mimeTypes = {"image/jpeg", "image/png"};
            intent.putExtra(Intent.EXTRA_MIME_TYPES,mimeTypes);
            // Launching the Intent            // Always show the chooser (if there are multiple options available)
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null)
        {
            Uri uri = data.getData();

            try
            {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                IMAGE_URI = uri;
                vehicleImage.setImageBitmap(bitmap);
                vehicleImage.setTag("gallery");
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
