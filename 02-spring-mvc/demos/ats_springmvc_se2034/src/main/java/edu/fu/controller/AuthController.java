package edu.fu.controller;

import edu.fu.entities.Users;
import edu.fu.service.AuthService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // --> Spring Bean
@RequestMapping(path = {"/auths", ""})
@RequiredArgsConstructor
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    private final AuthService authService;

    @RequestMapping(path = {"/", "/login"}, method = RequestMethod.GET)
    public String login() {
        return "auth/login";
    }

    @PostMapping(path = "/login")
    public String processLogin(@RequestParam("email") String email, @RequestParam("password") String password, Model model
                                    , HttpSession session) {
        log.info("email:" + email);
        log.info("password:" + password);

        Users user = authService.login(Users.builder().email(email).password(password).build());
        session.setAttribute("user",user);
        return "redirect:/jobs";
    }
}
