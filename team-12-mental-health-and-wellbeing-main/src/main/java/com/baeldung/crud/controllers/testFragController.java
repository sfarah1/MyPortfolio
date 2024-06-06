package com.baeldung.crud.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class testFragController {

    @GetMapping("/testFrag")
    public String ShowFragments(){

        return "testFrag";
    }
}
