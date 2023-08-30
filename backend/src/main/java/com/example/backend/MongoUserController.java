package com.example.backend;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class MongoUserController {

    private final MongoUserDetailService service;

    @PostMapping("/register")
    public String saveUser(@RequestBody MongoUser user){
        return service.saveUser(user).getUsername();
    }

    @PostMapping("/register/placeidwork")
    public String saveUserPlaceIdWork(@RequestBody MongoUser user){
        return service.saveUser(user).getUsername();
    }

    @PostMapping("/register/placeidhome")
    public String saveUserPlaceIdHome(@RequestBody MongoUser user){
        return service.saveUser(user).getUsername();
    }


    @Autowired
    private MongoUserRepo mongoUserRepo;

    @PostMapping("/register/placeid/{stadt}/{Strasse}/{Nummer}")
    public  ResponseEntity<String> PlaceId(@PathVariable String stadt, @PathVariable String strasse, @PathVariable String nummer) {
        try {

            return ResponseEntity.ok("Adresse erfolgreich registriert");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Adresse konnte nicht registriert werden");
        }
    }








    @GetMapping("/me2")
    public String getMe2(){
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