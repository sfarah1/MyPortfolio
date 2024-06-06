package com.baeldung.crud.controllers;

import com.baeldung.crud.form.AddRegisterUserForm;
import com.baeldung.crud.repositories.RegisterUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegisterUserController {
    //put registerUserRepository instantiation in RegisterController
    private RegisterUserRepository registerUserRepo;
    @Autowired
    public RegisterUserController(RegisterUserRepository pRepo) {
        registerUserRepo = pRepo;
    }
    @RequestMapping("/public/login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }


    @PostMapping("/public/toLogin")
    public ModelAndView toLogin(@RequestParam("username") String usernameString,
                                @RequestParam("password") String passwordString,
                                Model model) {
        ModelAndView mav = new ModelAndView();
        System.out.println(usernameString);
        System.out.println(passwordString);
        /*If the user name or password entered in the front end cannot be found in the database,
         a message is displayed indicating that the user name or password is incorrect.
        //If the verification is successful, the login page is displayed
        **/
        if (registerUserRepo.findUsernameAndPassword(usernameString, passwordString)) {

            mav.setViewName("aboutUs");
        } else {
            model.addAttribute("msg", "The user name or password is incorrect!");
            mav.setStatus(HttpStatus.BAD_REQUEST);
            mav.setViewName("login");
        }
        return mav;
    }

    @RequestMapping("/public/register")//when the url is /public/register execute this method
    public ModelAndView register(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("register");//return the register.html
        return mav;
    }

    //when the url is /public/registered execute this method
    @RequestMapping(path="/public/registered", method = RequestMethod.POST)
    public ModelAndView addRegisterUser(AddRegisterUserForm addRegisterUserForm, BindingResult br) {
        ModelAndView mav = new ModelAndView();
        if (br.hasErrors()) {
            mav.setViewName("register");//if have some errors ,that will return register.html
        } else {
            if (registerUserRepo.addRegisterUser(addRegisterUserForm)) {//when call the method return a ture
                System.out.println("added regieterUser");
                mav.setViewName("successfulRegister");//return successfulRegister.html
            }else{
                mav.setViewName("register");//if register fail,return false,that will return register.html
            }
        }
        return mav;
    }
}


