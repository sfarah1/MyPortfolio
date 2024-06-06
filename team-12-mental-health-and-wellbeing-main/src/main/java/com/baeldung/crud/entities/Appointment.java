package com.baeldung.crud.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message = "Date is mandatory")
    private String date;

    @NotBlank(message = "startTime is mandatory")
    private String startTime;

    @NotBlank(message = "endTime is mandatory")
    private String endTime;

    private long bookedBy;

    public Appointment() {
    }

    public Appointment(String date, String startTime, String endTime) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.bookedBy = 0;
    }

    public Appointment(String date, String startTime, String endTime, long bookedBy) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.bookedBy = bookedBy;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public long getBookedBy() {
        return bookedBy;
    }

    public void setBookedBy(long bookedBy) {
        this.bookedBy = bookedBy;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", bookedBy=" + bookedBy +
                '}';
    }
}
