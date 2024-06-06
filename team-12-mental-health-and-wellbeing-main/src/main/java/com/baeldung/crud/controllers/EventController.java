package com.baeldung.crud.controllers;

import com.baeldung.crud.form.AddEventForm;
import com.baeldung.crud.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

@Controller
public class EventController {
    private EventRepository eventRepo;

    @Autowired
    public EventController(EventRepository  pRepo) {
        eventRepo = pRepo;
    }

    @RequestMapping("/admin/addEvent")
    public ModelAndView Add() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("add-event");
        return mav;
    }

    @RequestMapping("/public/event")
    public ModelAndView event() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("events", eventRepo.findAllEvents());
        mav.setViewName("events");
        return mav;
    }

    @RequestMapping("/admin/allEventsDetails")
    public ModelAndView allEventsDetails() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("eventdetails",eventRepo.findAllEvents());
        mav.setViewName("allEventsDetails");
        return mav;
    }

    @RequestMapping(path="/admin/toAddEvent", method = RequestMethod.POST)
    public ModelAndView AddEvent(@RequestParam("eventimg") MultipartFile file,AddEventForm addEventForm) throws Exception {
        String filePath = "src/main/resources/static/img/eventImg/" + file.getOriginalFilename();
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filePath));
        outputStream.write(file.getBytes());
        outputStream.flush();
        outputStream.close();
        String imgPath = "/img/eventImg/" + file.getOriginalFilename();
        ModelAndView mav = new ModelAndView();
        if (eventRepo.addEvent(addEventForm,imgPath)) {
                System.out.println("added event");
                mav.addObject("events", eventRepo.findAllEvents());
                mav.setViewName("events");
        }else{
                mav.setViewName("index");
        }

        return mav;
    }

    @PostMapping("/admin/deleteEvent")
    public ModelAndView delete(@RequestParam("event_id") int intID){
        ModelAndView mav = new ModelAndView();
        System.out.println("deleted a event");
        mav.addObject(eventRepo.deleteEvent(intID));
        mav.setViewName("redirect:allEventsDetails");
        return mav;
    }

}
