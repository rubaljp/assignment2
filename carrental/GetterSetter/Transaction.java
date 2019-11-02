package com.example.carrental.GetterSetter;

import java.util.ArrayList;

public class Transaction
{
    private static ArrayList<Rental> rentals;
    private static ArrayList<Reservation> reservations;
    private static ArrayList<Return> returns;

    public ArrayList<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(ArrayList<Rental> rentals) {
        this.rentals = rentals;
    }

    public ArrayList<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(ArrayList<Reservation> reservations) {
        this.reservations = reservations;
    }

    public ArrayList<Return> getReturns() {
        return returns;
    }

    public void setReturns(ArrayList<Return> returns) {
        this.returns = returns;
    }
}
