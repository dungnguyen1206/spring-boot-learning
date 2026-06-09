package edu.fu.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // --> Spring Bean
@RequestMapping(path = {"/auths",""})
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    @RequestMapping(path = {"/","/login"}, method = RequestMethod.GET)
    public String login(){
     return "auth/login";
    }

    @PostMapping(path = "/login")
    public String processLogin(@RequestParam String email, @RequestParam String password, Model model){
        log.info("email:"+email);
        log.info("password:"+password);
        if(!"rec@example.com".equals(email)){
            model.addAttribute("error","Email or password is incorrect");
            return "auth/login";
        }
        return "jobs/job_managerment";
    }
}
