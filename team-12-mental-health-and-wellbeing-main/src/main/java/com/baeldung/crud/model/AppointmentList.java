package com.baeldung.crud.model;

import com.baeldung.crud.entities.Appointment;

import java.util.List;

public class AppointmentList {
    String date;
    List<Appointment> value;

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public List<Appointment> getValue() {
        return value;
    }

    public void setValue(List<Appointment> value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "AppointmentList{" +
                "date='" + date + '\'' +
                ", value=" + value +
                '}';
    }
}
