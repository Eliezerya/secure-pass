package com.eliezer.securepass.web.Controller;

import com.eliezer.securepass.Domain.User;
import com.eliezer.securepass.Reporsitory.UserRepository;
import com.eliezer.securepass.Service.LoginService;
import com.eliezer.securepass.web.Helper.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@CrossOrigin
public class LoginController {

    LoginService loginService;

    UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/registration")
    public String saveUser(@ModelAttribute("user") User user, HttpSession session){

            try {
                if (userRepository.findByEmail(user.getEmail())!=null){
                    session.setAttribute("message",new Message("Email already Used", "failed"));
                    return "redirect:/registration?failed";
                }
                session.setAttribute("message",new Message("Account Already Created", "succes"));
                loginService.saveUser(user);
                return "redirect:/registration?succes";
            }catch (Exception e){
                session.setAttribute("message",new Message("Something Went Wrong...!", "failed"));
                return "redirect:/registration";
            }

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
