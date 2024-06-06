package com.baeldung.crud.repositories;

import com.baeldung.crud.entities.Appointment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AppointmentRepository extends CrudRepository<Appointment, Long> {

    List<Appointment> findByBookedBy(long bookedBy);
    List<Appointment> findByDate(String date);
    List<Appointment> findByDateAndBookedBy(String date,long BookedBy);

}
