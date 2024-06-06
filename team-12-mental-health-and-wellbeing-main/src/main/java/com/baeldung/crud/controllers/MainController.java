package com.baeldung.crud.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    @RequestMapping("/chatapp")
    public String index(HttpServletRequest request, Model model) {
        String username = (String)request.getSession().getAttribute("username");

        if(username == null || username.isEmpty()){
            return "redirect:/chatlogin";
        }
        model.addAttribute("username", username);

        return "chat";
    }

    @RequestMapping(path ="/chatlogin", method = RequestMethod.GET)
    public String showLoginPage(){
        return "chatlogin";
    }

    @RequestMapping(path = "/chatlogin", method = RequestMethod.POST)
    public String doLogin(HttpServletRequest request, @RequestParam(defaultValue = "") String username){
        username = username.trim();

        if(username.isEmpty()){
            return "chatlogin";
        }
//        Critical area that I was making mistakes in
        request.getSession().setAttribute("username", username);
        return "redirect:/chatapp";
    }

    @RequestMapping(path = "/logout")
    public String logout(HttpServletRequest request){
        request.getSession(true).invalidate();

        return "redirect:/chatlogin";
    }
}
