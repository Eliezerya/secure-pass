package com.eliezer.securepass.web.Controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.eliezer.securepass.Domain.Account;
import com.eliezer.securepass.Domain.User;
import com.eliezer.securepass.Service.LoginService;
import com.eliezer.securepass.web.Dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@RequestMapping("/registration")
@CrossOrigin
public class LoginController {

    LoginService loginService;

    UserDetailsService userDetailsService;

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

//    @PostMapping("/registrasi") //register
//    public ResponseEntity<?> submit_user_buyer(@RequestBody UserDto userDto) {
//        User userLogin = loginService.findByEmail(userDto.getEmail());
//        if (userLogin != null) {
//            return new ResponseEntity<>(userLogin, HttpStatus.CONFLICT);
//        } else {
//            loginService.saveUser(userDto);
//            return new ResponseEntity<>("Registrasi Berhasil", HttpStatus.CREATED);
//        }
//    }
//    @GetMapping
//    public String displayRegistrationPage(){
//        return "registration";
//    }
    @PostMapping
    public String saveUser(@ModelAttribute("user") User user){

        loginService.saveUser(user);
        return "redirect:/registration?succes";
    }

    @GetMapping
    public String createUserForm(Model model) {
        User user = new User();

        model.addAttribute("user", user);
        return "registration";
    }

    // handler method to handle login request
    @GetMapping("/login")
    public String login(Model model){
        return "login";
    }


}
