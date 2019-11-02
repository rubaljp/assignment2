package com.example.carrental.Others;

import com.example.carrental.Activities.UserDetails;
import com.example.carrental.Activities.VehicleDetails;
import com.example.carrental.Activities.ViewVehicleDetails;
import com.example.carrental.GetterSetter.Rental;
import com.example.carrental.GetterSetter.Reservation;
import com.example.carrental.GetterSetter.User;
import com.example.carrental.GetterSetter.Vehicle;

import java.util.Random;

public class Utils
{
    public static String hasData = "0";
    public static String IsUserView = null;

    public static int getVehicleIndex(String uniqueID)
    {
        for(Vehicle vehicle : VehicleDetails.vehicles)
        {
            if(uniqueID.equals(vehicle.getUniqueID().trim()))
            {
                int index = VehicleDetails.vehicles.indexOf(vehicle);
                return index;
            }
        }
        return 0;
    }

    public static int getUserIndex(String uniqueID)
    {
        for(User user : UserDetails.users)
        {
            if(uniqueID.equals(user.getUniqueId()))
            {
                int index = UserDetails.users.indexOf(user);
                return index;
            }
        }
        return 0;
    }

    public static String getRandomNumber()
    {
        Random random = new Random();
        String generatedNumber = String.format("%04d", random.nextInt(10000));
        return generatedNumber;
    }

    public static boolean checkVehicleIsRented(Vehicle vehicle)
    {
        if(ViewVehicleDetails.transactions.size() > 0)
        {
            for(int i = 0; i < ViewVehicleDetails.transactions.size(); i++)
            {
                if(ViewVehicleDetails.transactions.get(i).getRentals() != null)
                {
                    for(Rental rentalVehicles : ViewVehicleDetails.transactions.get(i).getRentals())
                    {
                        if(vehicle.getLicensePlate().equals(rentalVehicles.getLicensePlate().trim()))
                        {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static boolean checkVehicleIsReserved(Vehicle vehicle)
    {
        if(ViewVehicleDetails.transactions.size() > 0)
        {
            for(int i = 0; i < ViewVehicleDetails.transactions.size(); i++)
            {
                if(ViewVehicleDetails.transactions.get(i).getReservations() != null)
                {
                    for(Reservation reservedVehicles : ViewVehicleDetails.transactions.get(i).getReservations())
                    {
                        if(vehicle.getLicensePlate().equals(reservedVehicles.getLicensePlate().trim()))
                        {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}