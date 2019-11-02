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

import com.example.carrental.GetterSetter.Vehicle;
import com.example.carrental.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class VehicleDetails extends AppCompatActivity implements View.OnClickListener
{
    ImageView vehicleImage;
    EditText edtType, edtBrand, edtModel, edtYear, edtColor, edtLicensePlate;
    Button btnSaveDetails, btnUpdateDetails;

    private int PICK_IMAGE_REQUEST = 1;
    private Uri IMAGE_URI = null;

    public static ArrayList<Vehicle> vehicles = new ArrayList<>();

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

        btnSaveDetails = (Button) findViewById(R.id.btnSaveDetails);
        btnSaveDetails.setVisibility(View.VISIBLE);

        btnSaveDetails.setOnClickListener(this);
        vehicleImage.setOnClickListener(this);
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, EmployeeOperation.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View view)
    {
        int resourceId = view.getId();

        if(resourceId == R.id.btnSaveDetails)
        {
            if("gallery".equals(vehicleImage.getTag()) && !edtType.getText().toString().equals("") && !edtBrand.getText().toString().equals("") &&
                    !edtModel.getText().toString().equals("") && !edtYear.getText().toString().equals("") &&
                            !edtColor.getText().toString().equals("") && !edtLicensePlate.getText().toString().equals(""))
            {
                Vehicle vehicle = new Vehicle();

                vehicle.setType(edtType.getText().toString().trim());
                vehicle.setBrand(edtBrand.getText().toString().trim());
                vehicle.setModel(edtModel.getText().toString().trim());
                vehicle.setYear(edtYear.getText().toString().trim());
                vehicle.setColor(edtColor.getText().toString().trim());
                vehicle.setLicensePlate(edtLicensePlate.getText().toString().trim());
                vehicle.setImageUri(IMAGE_URI);

                if(vehicles.size() > 0)
                {
                    for(Vehicle existingVehicle : vehicles)
                    {
                        if(edtLicensePlate.getText().toString().trim().equals(existingVehicle.getLicensePlate()))
                        {
                            Toast.makeText(this, "License Plate No. Already Exist", Toast.LENGTH_SHORT).show();
                        }
                        else if(!edtLicensePlate.getText().toString().trim().equals(existingVehicle.getLicensePlate()))
                        {
                            String uniqueID = UUID.randomUUID().toString();
                            vehicle.setUniqueID(uniqueID);
                            vehicles.add(vehicle);
                            Toast.makeText(this, "Vehicle Created Successfully", Toast.LENGTH_SHORT).show();

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
                else
                {
                    String uniqueID = UUID.randomUUID().toString();
                    vehicle.setUniqueID(uniqueID);
                    vehicles.add(vehicle);
                    Toast.makeText(this, "Vehicle Created Successfully", Toast.LENGTH_SHORT).show();

                    edtType.setText("");
                    edtBrand.setText("");
                    edtModel.setText("");
                    edtYear.setText("");
                    edtColor.setText("");
                    edtLicensePlate.setText("");
                    vehicleImage.setImageResource(R.drawable.ic_gallery);
                }

            }
            else
            {
                Toast.makeText(this, "Please fill the entire details !", Toast.LENGTH_SHORT).show();
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
            intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
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
