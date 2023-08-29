package com.example.backend;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
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






    @PostMapping("/Login")
    public String login() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}