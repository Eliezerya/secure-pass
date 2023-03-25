package com.eliezer.securepass.web.Controller;

import com.eliezer.securepass.Domain.User;
import com.eliezer.securepass.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
public class LoginController {

    LoginService loginService;




    @Autowired
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/registration")
    public String saveUser(@ModelAttribute("user") User user){

        loginService.saveUser(user);
        return "redirect:/registration?succes";
    }

    @GetMapping("/registration")
    public String createUserForm(Model model) {
        User user = new User();

        model.addAttribute("user", user);
        return "registration";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }




}
