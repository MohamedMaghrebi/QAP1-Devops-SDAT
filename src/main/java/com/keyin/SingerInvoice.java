package com.keyin;

public class SingerInvoice {
    private Singer singer;
    private int hoursWorked;
    private double ratePerHour;

    public SingerInvoice(Singer singer, int hoursWorked, double ratePerHour) {
        this.singer = singer;
        this.hoursWorked = hoursWorked;
        this.ratePerHour = ratePerHour;
    }

    public double calculateTotalPayment() {
        return hoursWorked * ratePerHour;
    }

    @Override
    public String toString() {
        return "SingerInvoice[singer=" + singer.getFullName() + ", hoursWorked=" + hoursWorked + ", ratePerHour=" + ratePerHour + ", totalPayment=" + calculateTotalPayment() + "]";
    }
}