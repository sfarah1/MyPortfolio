package com.baeldung.crud.controllers;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;


@Controller
public class AboutUsController {

      @Autowired
      private JavaMailSender mailSender;

     /*using @Value annotation to inject the value of email username to avoid hardcoding email for
     * best practice*/
      @Value("${spring.mail.username}")
      private String targetEmail;

    @GetMapping("/AboutUs")
    public String ShowAboutUs(){

        return "aboutUs";
    }



    @PostMapping("/AboutUs")
    public String submitForm(@NotNull HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String subject = request.getParameter("subject");
        String messageInput = request.getParameter("messageInput");

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);



        String mailSubject = name + " has sent a message";
        String mailContent = "<p><b>Sender Name:</b> " + name + "</p>";
        mailContent += "<p><b>Sender E-mail:</b> " + email + "</p>";
        mailContent += "<p><b>Subject:</b> " + subject + "</p>";
        mailContent += "<p><b>Message:</b> " + messageInput + "</p>";
        mailContent += "<hr><img src='cid:logo'/>";

        helper.setFrom("to", "Contact Us Form");
        helper.setTo(targetEmail);
        helper.setSubject(mailSubject);
        helper.setText(mailContent, true);

        ClassPathResource resource = new ClassPathResource("/static/images/tidybutt-logo.png");
        helper.addInline("logo", resource);

        mailSender.send(message);





        return "messageReceived";
    }


}
