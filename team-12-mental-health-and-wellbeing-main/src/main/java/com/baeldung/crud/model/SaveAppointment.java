package com.baeldung.crud.model;

public class SaveAppointment {
    public Long appointment_id;
    public Long user_id;


    public Long getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(Long appointment_id) {
        this.appointment_id = appointment_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "SaveAppointment{" +
                "appointment_id=" + appointment_id +
                ", user_id=" + user_id +
                '}';
    }
}
