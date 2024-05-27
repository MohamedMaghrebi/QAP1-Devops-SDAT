package com.keyin;

import java.util.Objects;

public class Singer {
    private int id;
    private String firstName;
    private String lastName;
    private int monthlySalary;

    public Singer(int id, String firstName, String lastName, int monthlySalary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.monthlySalary = monthlySalary;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public int getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(int monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    public int getAnnualSalary() {
        return monthlySalary * 12;
    }

    public int raiseSalary(int percent) {
        int increase = (monthlySalary * percent) / 100;
        monthlySalary += increase;
        return monthlySalary;
    }

    @Override
    public String toString() {
        return "Singer[id=" + id + ", name=" + getFullName() + ", salary=" + monthlySalary + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Singer singer = (Singer) o;
        return firstName.equals(singer.firstName) && lastName.equals(singer.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}