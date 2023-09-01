package com.example.backend;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class MongoUserController {

    private final MongoUserDetailService service;


    @PostMapping("/register")
    public String saveUser(@RequestBody MongoUser user) {


            return service.saveUser(user).getUsername();
        }


    @PostMapping("/register/placeidwork/{sarbeitsadressstadt}/{arbeitsadressestrasse}/{arbeitsadressenummer}")
    public MongoUser saveuserplaceidwork(@RequestBody MongoUser user, @PathVariable String sarbeitsadressstadt, @PathVariable String arbeitsadressestrasse, @PathVariable String arbeitsadressenummer) {
        user.setArbeitsadressestrasse(user.arbeitsadressestrasse);
        user.setArbeitsadressestrasse(user.arbeitsadressestrasse);
        user.setArbeitsadressestrasse(user.arbeitsadressenummer);

        return user;

    }
/*
    @PostMapping("/register/placeidhome")
    public String saveUserPlaceIdHome(@RequestBody MongoUser user){
        return service.saveUserPlaceIdWork();
    }
*/

    @Autowired
    private MongoUserRepo mongoUserRepo;


    @GetMapping("/me2")
    public String getMe2() {
        return SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

    }

    @PostMapping("/logout")
    public String login(HttpSession httpSession) {
        httpSession.invalidate();
        SecurityContextHolder.clearContext();
        return "logged out";

    }


    @PostMapping("/login")
    public String login() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }


}