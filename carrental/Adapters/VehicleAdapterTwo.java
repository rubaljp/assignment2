package com.example.carrental.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.carrental.GetterSetter.Vehicle;
import com.example.carrental.R;

import java.io.IOException;
import java.util.ArrayList;

public class VehicleAdapterTwo extends BaseAdapter
{

    Context context;
    ArrayList<Vehicle> vehicles = new ArrayList<>();

    public VehicleAdapterTwo(Context context, ArrayList<Vehicle> vehicles)
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
        View view = layoutInflater.inflate(R.layout.layout_vehicles_two, parent, false);

        ImageView vehicleImage = (ImageView) view.findViewById(R.id.vehicleImage);
        TextView carBrand = (TextView) view.findViewById(R.id.carBrand);
        TextView carModel = (TextView) view.findViewById(R.id.carModel);

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

        carBrand.setText(vehicle.getBrand());
        carModel.setText(vehicle.getModel());

        return view;
    }
}