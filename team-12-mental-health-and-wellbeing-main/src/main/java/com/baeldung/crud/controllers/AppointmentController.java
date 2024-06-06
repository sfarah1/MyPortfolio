package com.baeldung.crud.controllers;

import com.baeldung.crud.entities.Appointment;
import com.baeldung.crud.model.AppointmentList;
import com.baeldung.crud.model.SaveAppointment;
import com.baeldung.crud.repositories.AppointmentRepository;
import com.baeldung.crud.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

@Controller
public class AppointmentController {
    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;

    @Autowired
    public AppointmentController(AppointmentRepository appointmentRepository, UserRepository userRepository) {
        this.appointmentRepository = appointmentRepository;
        this.userRepository = userRepository;
    }

    public List<AppointmentList> getDateWiseData() {
        List output = new ArrayList();
        for (Appointment element : appointmentRepository.findAll()) {
            output.add(element.getDate());
        }
        List<String> listWithoutDuplicates = new ArrayList<>(
                new HashSet<>(output));
        output.clear();

        for (String date: listWithoutDuplicates
        ) {
            AppointmentList output_web = new AppointmentList();
            output_web.setDate(date);
            List<Appointment> temp = appointmentRepository.findByDateAndBookedBy(date,0);
            if(!temp.isEmpty()) {
            temp.sort(Comparator.comparing(Appointment::getStartTime));
            output_web.setValue(temp);
            output.add(output_web);}
        }
        output.sort(Comparator.comparing(AppointmentList::getDate));
        return output;
    }
    @GetMapping("/appointments")
    public String showAppointmentList(Model model) {
        model.addAttribute("appointmentList", getDateWiseData());
        return "appointment";
    }

    @PostMapping("/saveAppointment")
    public String saveAppointment(@RequestBody SaveAppointment body) {
        userRepository.findById(body.user_id).ifPresent( user -> {
            appointmentRepository.findById(body.appointment_id).ifPresent(appointment -> {
                appointment.setBookedBy(body.user_id);
                appointmentRepository.save(appointment);
            });
        });
        return "appointment";
    }

    @GetMapping("/admin/appointments")
    public String showAdminAppointmentList(Model model) {
        model.addAttribute("appointments", appointmentRepository.findAll());
        return "admin-appointment";
    }

    @GetMapping("/admin/appointments/add")
    public String showAddAppointment(Appointment appointment) {
        return "add-appointment";
    }


    @PostMapping("/admin/appointments/add")
    public String addAppointment(@Valid Appointment appointment, BindingResult result, Model model) throws Exception {
        if (result.hasErrors() ) {
            return "add-appointment";
        }
        appointmentRepository.save(appointment);
        return "redirect:/admin/appointments";
    }

    @GetMapping("/admin/appointments/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid appointment Id:" + id));
        model.addAttribute("appointments", appointment);
        return "update-appointment";
    }

    @PostMapping("/admin/appointments/update/{id}")
    public String updateAppointment(@PathVariable("id") long id, @Valid Appointment appointment, BindingResult result, Model model) throws Exception {
        if (result.hasErrors()) {
            appointment.setId(id);
            return "update-appointment";
        }

        appointmentRepository.save(appointment);

        return "redirect:/admin/appointments";
    }

    @GetMapping("/admin/appointments/delete/{id}")
    public String deleteAppointment(@PathVariable("id") long id, Model model) {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid appointment Id:" + id));
        appointmentRepository.delete(appointment);

        return "redirect:/admin/appointments";
    }
}
