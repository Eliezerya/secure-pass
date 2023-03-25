package com.eliezer.securepass.Service;

import com.eliezer.securepass.Domain.Role;
import com.eliezer.securepass.Domain.User;
import com.eliezer.securepass.Reporsitory.RoleRepo;
import com.eliezer.securepass.Reporsitory.UserRepository;
import com.eliezer.securepass.web.Dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService,UserDetailsService {

    @Autowired
    UserRepository userRepository;

    PasswordEncoder passwordEncoder;

    RoleRepo roleRepo;

    @Autowired
    public void setRoleRepo(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //set role
        List<Role> getRole = roleRepo.findByIdRole(1); //buyer
        user.setRoles(getRole);
//        user.setEmail((userDto.getEmail()));
        return userRepository.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            System.out.println("Not Found");
        } else {
            System.out.println("Found");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(roles -> authorities.add(new SimpleGrantedAuthority(roles.getRoleName())));
        return new org.springframework.security.core.userdetails.
                User(user.getEmail(), user.getPassword(), authorities);
    }
}
