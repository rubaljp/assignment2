package com.example.carrental.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carrental.GetterSetter.Vehicle;
import com.example.carrental.R;

import java.io.IOException;
import java.util.ArrayList;

public class VehicleAdapter extends BaseAdapter
{
    Context context;
    ArrayList<Vehicle> vehicles;

    public VehicleAdapter(Context context, ArrayList<Vehicle> vehicles)
    {
        this.context = context;
        this.vehicles = vehicles;
    }

    @Override
    public int getCount()
    {
        return vehicles.size();
    }

    @Override
    public Object getItem(int position)
    {
        return vehicles.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.layout_vehicles, parent, false);

        ImageView vehicleImage = (ImageView) view.findViewById(R.id.vehicleImage);
        TextView vehicleType = (TextView) view.findViewById(R.id.vehicleType);
        TextView vehicleBrand = (TextView) view.findViewById(R.id.vehicleBrand);
        TextView vehicleModel = (TextView) view.findViewById(R.id.vehicleModel);
        TextView vehicleYear = (TextView) view.findViewById(R.id.vehicleYear);
        TextView vehicleColor = (TextView) view.findViewById(R.id.vehicleColor);
        TextView vehicleLicensePlate = (TextView) view.findViewById(R.id.vehicleLicensePlate);

        Vehicle vehicle = vehicles.get(position);

        try
        {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), vehicle.getImageUri());
            vehicleImage.setImageBitmap(bitmap);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        vehicleType.setText(vehicle.getType());
        vehicleBrand.setText(vehicle.getBrand());
        vehicleModel.setText(vehicle.getModel());
        vehicleYear.setText(vehicle.getYear());
        vehicleColor.setText(vehicle.getColor());
        vehicleLicensePlate.setText(vehicle.getLicensePlate());

        return view;
    }
}
