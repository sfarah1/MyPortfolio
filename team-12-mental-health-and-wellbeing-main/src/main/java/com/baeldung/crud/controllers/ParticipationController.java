package com.baeldung.crud.controllers;

import com.baeldung.crud.DTO.ParticipationDTO;
import com.baeldung.crud.repositories.ParticipationRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@RestController
public class ParticipationController {
    @Resource
    private ParticipationRepository participationRepo;

    @RequestMapping(path = "/admin/participationAll", method = RequestMethod.GET)
    public ModelAndView search() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("participations", participationRepo.findAllParticipation());
        mav.setViewName("allParticipationDetails");
        return mav;
    }

    @PostMapping("/admin/participationAll/add")
    public int add(@RequestBody ParticipationDTO participationDTO) {
        System.out.println(participationDTO);
        int res = participationRepo.addParticipation(participationDTO);
        /*
        0失败 1成功
         */

        return res;
    }
    @GetMapping("/admin/deleteParticipation")
    public ModelAndView delete(@RequestParam("participation_id") int intID){
        ModelAndView mav = new ModelAndView();
        System.out.println("deleted a participant");
        mav.addObject(participationRepo.deleteParticipation(intID));
        mav.setViewName("allParticipationDetails");
        return mav;
    }


}
