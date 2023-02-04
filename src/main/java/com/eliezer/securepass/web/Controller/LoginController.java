//package com.eliezer.securepass.web.Controller;
//
//import com.eliezer.securepass.Domain.User;
//import com.eliezer.securepass.Service.LoginService;
//import com.eliezer.securepass.web.Dto.UserDto;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@CrossOrigin
//public class    LoginController {
//
//    LoginService loginService;
//
//    @Autowired
//    public void setLoginService(LoginService loginService) {
//        this.loginService = loginService;
//    }
//
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
//
//
//}
