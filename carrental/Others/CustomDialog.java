package com.example.carrental.Others;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.example.carrental.Activities.EmployeeOperation;
import com.example.carrental.Activities.ModfiyVehicleDetails;
import com.example.carrental.Activities.ModifyUserDetails;
import com.example.carrental.Activities.UserDetails;
import com.example.carrental.Activities.VehicleDetails;
import com.example.carrental.Adapters.VehicleAdapter;
import com.example.carrental.R;

import java.util.Objects;

public class CustomDialog
{
    private static boolean isRented = false;
    private static boolean isReserved = false;

    public static boolean customerDialog(final Activity activity, final int position)
    {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setContentView(R.layout.information_dialog);
        Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);

        Button modifyButton = (Button) dialog.findViewById(R.id.modifyButton);
        Button deleteButton = (Button) dialog.findViewById(R.id.deleteButton);

        if(Utils.IsUserView.equals("Customers")) {
            modifyButton.setText("Modify Customer");
            deleteButton.setText("Delete Customer");
        }
        else if(Utils.IsUserView.equals("Vehicles")) {
            modifyButton.setText("Modify Vehicle");
            deleteButton.setText("Delete Vehicle");
        }

        if(VehicleDetails.vehicles.size() > 0)
        {
            isRented = Utils.checkVehicleIsRented(VehicleDetails.vehicles.get(position));
            isReserved = Utils.checkVehicleIsReserved(VehicleDetails.vehicles.get(position));
        }

        modifyButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(dialog.isShowing())
                {
                    dialog.dismiss();
                    if(Utils.IsUserView.equals("Customers"))
                    {
                        Intent intent = new Intent(activity, ModifyUserDetails.class);
                        intent.putExtra("position", position);
                        activity.startActivity(intent);
                        activity.finish();
                    }
                    else if(Utils.IsUserView.equals("Vehicles"))
                    {
                        if(!isRented && !isReserved)
                        {
                            Intent intent = new Intent(activity, ModfiyVehicleDetails.class);
                            intent.putExtra("position", position);
                            activity.startActivity(intent);
                            activity.finish();
                        }
                        else if(isRented)
                            Toast.makeText(activity, "Vehicle is Rented; Cannot be modify !", Toast.LENGTH_SHORT).show();
                        else if(isReserved)
                            Toast.makeText(activity, "Vehicle is Reserved; Cannot be modify !", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                if(dialog.isShowing())
                {
                    dialog.dismiss();
                    if(Utils.IsUserView.equals("Customers"))
                    {
                        ArrayAdapter customerAdapter = EmployeeOperation.getInstance().customerAdapter;
                        UserDetails.users.remove(position);
                        customerAdapter.remove(customerAdapter.getItem(position));
                        customerAdapter.notifyDataSetChanged();

                        if(UserDetails.users.size() == 0)
                            Utils.hasData = "0";

                        Toast.makeText(activity, "Customer Deleted Successfully.", Toast.LENGTH_SHORT).show();
                    }
                    else if(Utils.IsUserView.equals("Vehicles"))
                    {
                        if(!isRented && !isReserved)
                        {
                            VehicleAdapter vehicleAdapter = EmployeeOperation.getInstance().vehicleAdapter;

                            VehicleDetails.vehicles.remove(position);
                            vehicleAdapter.notifyDataSetChanged();
                        }
                        else if(isRented)
                            Toast.makeText(activity, "Vehicle is Rented; Cannot be deleted !", Toast.LENGTH_SHORT).show();
                        else if(isReserved)
                            Toast.makeText(activity, "Vehicle is Reserved; Cannot be deleted !", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogInterface, int keyCode, KeyEvent keyEvent) {
                if(dialog.isShowing())
                {
                    if (keyCode == KeyEvent.KEYCODE_BACK)
                    {
                        dialog.dismiss();
                    }
                }
                return true;
            }
        });

        dialog.show();
        return false;
    }
}